package packt;

import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
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

}
