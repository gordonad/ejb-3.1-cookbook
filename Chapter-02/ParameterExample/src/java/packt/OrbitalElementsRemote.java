package packt;

import javax.ejb.Remote;

@Remote
public interface OrbitalElementsRemote {
    public PositionBean getPosition();
}
