package packt;

import javax.ejb.Singleton;
import javax.inject.Named;

@Singleton
@Named("orbitalElements")

public class OrbitalElements implements OrbitalElementsRemote {

    public PositionBean getPosition() {
        return new PositionBean();
    }
 
}
