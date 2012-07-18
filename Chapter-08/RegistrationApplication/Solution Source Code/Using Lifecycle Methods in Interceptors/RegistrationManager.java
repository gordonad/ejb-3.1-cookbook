package packt;

import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
@TransactionManagement(TransactionManagementType.BEAN)
public class RegistrationManager {
    
    @EJB
    AttendeeFacade attendeeFacade;

    Attendee attendee;

    @PersistenceContext(unitName = "RegistrationApplication-ejbPU")
    private EntityManager entityManager;

    @Interceptors(ValidationInterceptor.class)
    @Interceptors({HitCounterInterceptor.class,TimeInMethodInterceptor.class})
    public Attendee register(String name, String title, String company) {
        System.out.println("register");
        attendee = new Attendee(name, title, company);
        attendeeFacade.create(attendee);
        return attendee;
    }

    @Interceptors({TransactionInterceptor.class})
    public void bulkRegister(String names[], String titles[], String company) {
        for(int i=0; i<names.length; i++) {
            attendeeFacade.create(new Attendee(names[i],titles[i],company));
        }
    }

}
