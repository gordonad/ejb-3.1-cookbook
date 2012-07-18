package packt;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AttendeeFacade extends AbstractFacade<Attendee> {
    @PersistenceContext(unitName = "RegistrationApplication-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public AttendeeFacade() {
        super(Attendee.class);
    }

}
