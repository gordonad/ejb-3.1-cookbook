package packt;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Named;

@Singleton
@Startup
//@Named("applicationStateBean")
public class ApplicationStateBean {
    public enum States {PENDING, STARTED, PAUSED, TERMINATING};
    private States state;

    @PostConstruct
    public void initialize() {
        state = States.PENDING;
        // Perform intialization
        state = States.STARTED;
        System.out.println("---ApplicationStateBean Started");
    }

    @PreDestroy
    public void terminate() {
        state = States.TERMINATING;
        // Perform termination
        System.out.println("---ApplicationStateBean Terminating");
    }

    public States getState() {
        return state;
    }

    public void setState(States state) {
        this.state = state;
    }

}
