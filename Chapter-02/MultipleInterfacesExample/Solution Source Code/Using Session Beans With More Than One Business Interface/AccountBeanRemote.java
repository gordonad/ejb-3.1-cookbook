package packt;

import javax.ejb.Remote;

@Remote
public interface AccountBeanRemote {
    public float getCorporateDiscount();
    public float getNonProfitDiscount();
}
