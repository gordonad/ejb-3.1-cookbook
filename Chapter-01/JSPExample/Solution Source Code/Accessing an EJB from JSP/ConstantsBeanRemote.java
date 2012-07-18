package packt;

import javax.ejb.Remote;

@Remote
public interface ConstantsBeanRemote {
       public double getPI();
       public double getGoldenRatio();
}
