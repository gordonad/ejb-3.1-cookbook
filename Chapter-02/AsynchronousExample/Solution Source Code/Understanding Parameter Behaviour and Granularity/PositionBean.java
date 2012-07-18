package packt;

import javax.ejb.Stateless;
import javax.ejb.Remote;
import javax.ejb.Startup;
import javax.inject.Named;

@Stateless
@Remote
@Startup
@Named("position")
public class PositionBean implements PositionBeanRemote{

    // http://en.wikipedia.org/wiki/Orbital_elements

    private double eccentricity;
    private double semimajorAxis;
    private double inclination;
    private double longitudeOfTheAscendingNode;
    private double argumentOfPeriapsis;
    private double meanAnomaly;

    //@PostConstruct
    public PositionBean() {
        eccentricity = 1.0;
        System.out.println("--- PositionBean initialized");
    }
    public double getArgumentOfPeriapsis() {
        return argumentOfPeriapsis;
    }

    public double getEccentricity() {
        System.out.println("--- Return eccentricity");
        return eccentricity;
    }

    public double getInclination() {
        return inclination;
    }

    public double getLongitudeOfTheAscendingNode() {
        return longitudeOfTheAscendingNode;
    }

    public double getMeanAnomaly() {
        return meanAnomaly;
    }

    public double getSemimajorAxis() {
        return semimajorAxis;
    }


}
