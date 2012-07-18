package packt;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;

@Singleton
@LocalBean
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class SimulationContainerManaged {
    public enum State {PAUSED, RUNNING, TERMINATED};
    private State state;

    @Lock(LockType.READ)
    public State getState() {
        return state;
    }

    @Lock(LockType.WRITE)
    public void setState(State state) {
        this.state = state;
    }


}
