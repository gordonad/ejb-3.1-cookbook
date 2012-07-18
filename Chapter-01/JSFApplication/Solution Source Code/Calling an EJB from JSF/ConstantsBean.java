package packt;

import javax.ejb.Stateless;
import javax.inject.Named;

@Stateless
@Named("constants")
public class ConstantsBean {

    public double getPI() {
        return Math.PI;
    }

    public double getGoldenRatio() {
        return 1.6180339887;
    }
    
}
