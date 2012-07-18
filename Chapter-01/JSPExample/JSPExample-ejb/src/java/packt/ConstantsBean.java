package packt;

import javax.ejb.Stateless;

@Stateless
public class ConstantsBean implements ConstantsBeanRemote {

    public double getPI() {
        return Math.PI;
    }

    public double getGoldenRatio() {
        return 1.6180339887;
    }
}
