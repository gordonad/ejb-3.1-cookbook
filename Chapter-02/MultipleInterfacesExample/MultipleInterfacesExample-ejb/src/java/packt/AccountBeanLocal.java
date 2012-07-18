package packt;

import javax.ejb.Local;

@Local
public interface AccountBeanLocal {
    public float getCorporateDiscount();
    public void setCorporateDiscount(float corporateDiscount);
    public float getNonProfitDiscount();
    public void setNonProfitDiscount(float nonProfitDiscount);
}
