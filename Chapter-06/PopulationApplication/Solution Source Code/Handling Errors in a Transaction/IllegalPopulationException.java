package packt;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class IllegalPopulationException extends Exception {
    public IllegalPopulationException() {

    }

    public IllegalPopulationException(String message) {
        super("IllegalPopulationException");
    }
}

