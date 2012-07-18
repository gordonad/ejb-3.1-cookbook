package packt;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class SphereBean {
    private String unit;
    private int count;

    @PostConstruct
    private void initialize() {
        unit = "meters";
        count = 0;
        System.out.println("SphereBean initialized");
    }

    @PreDestroy
    private void destroy() {
        System.out.println("Clean up SphereBean");
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double computeVolume(double radius) {
        count++;
        return (4.0/3.0)*Math.PI*(radius*radius*radius);
    }
 
}
