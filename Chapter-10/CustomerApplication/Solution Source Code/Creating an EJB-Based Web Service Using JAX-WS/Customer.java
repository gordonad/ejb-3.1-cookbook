package jaxws;
        
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import packt.CustomerManager;

@WebService()
public class Customer {
    @EJB
    private CustomerManager customerManager;

    @WebMethod
    public int getCustomerCount() {
        return customerManager.getCustomerCount();
    }

    @WebMethod
    public int getCustomerCountByRegion(String region) {
        return customerManager.getCustomerCountByRegion(region);
    }

}
