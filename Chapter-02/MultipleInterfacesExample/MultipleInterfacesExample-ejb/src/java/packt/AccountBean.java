package packt;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;

@Stateless
@Named("account")
public class AccountBean implements AccountBeanRemote, AccountBeanLocal {
    
    private float corporateDiscount;
    private float nonProfitDiscount;

    @PostConstruct
    public void initialize() {
        corporateDiscount = 0.15f;
        nonProfitDiscount = 0.25f;
        System.out.println("initialization of AccountBean");
    }
    public float getCorporateDiscount() {
        System.out.println("Get corporatediscount");
        return corporateDiscount;
    }

    public void setCorporateDiscount(float corporateDiscount) {
        this.corporateDiscount = corporateDiscount;
    }

    public float getNonProfitDiscount() {
        return nonProfitDiscount;
    }

    public void setNonProfitDiscount(float nonProfitDiscount) {
        this.nonProfitDiscount = nonProfitDiscount;
    }

 
}
