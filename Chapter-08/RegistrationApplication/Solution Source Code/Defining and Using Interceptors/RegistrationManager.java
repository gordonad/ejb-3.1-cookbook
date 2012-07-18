package packt;

import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
@Interceptors(SimpleInterceptor.class)
public class RegistrationManager {
    
    @EJB
    AttendeeFacade attendeeFacade;

    Attendee attendee;

    public Attendee register(String name, String title, String company) {
        System.out.println("register");
        attendee = new Attendee(name, title, company);
        attendeeFacade.create(attendee);
        return attendee;
    }

    @AroundInvoke
    public Object internalMethod(InvocationContext context) throws Exception{
        System.out.println("internalMethod: Invoking method: " + context.getMethod().getName());
        Object result =  context.proceed();
        System.out.println("internalMethod: Returned from method: " + context.getMethod().getName());
        return result;
    }


}
