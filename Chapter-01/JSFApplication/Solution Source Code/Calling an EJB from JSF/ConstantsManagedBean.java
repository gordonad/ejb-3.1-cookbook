package packt;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ConstantsManagedBean {

    @EJB
    ConstantsBean constants;

    public ConstantsManagedBean() {
    }

    public double getGoldenRatio() {
        return constants.getGoldenRatio();
    }

    public double getPI() {
        return constants.getPI();
    }

}