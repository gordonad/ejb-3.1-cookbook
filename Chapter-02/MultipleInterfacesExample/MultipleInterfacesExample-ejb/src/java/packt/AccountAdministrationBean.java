package packt;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;

@Singleton
@LocalBean
public class AccountAdministrationBean {
    @EJB
    AccountBean account;

    private void setRates() {
        account.setCorporateDiscount(0.25f);
        account.setNonProfitDiscount(0.35f);
    }
}
