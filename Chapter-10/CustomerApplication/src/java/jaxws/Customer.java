package jaxws;
        
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import packt.CustomerManager;

@WebService(serviceName="Customer")
public class Customer {
    @EJB
    private CustomerManager customerManager;

    @WebMethod(operationName="getTotalCount")
    public int getCustomerCount() {
        return customerManager.getCustomerCount();
    }

    @WebMethod
    public int getCustomerCountByRegion(@WebParam(name = "region") String region) {
        return customerManager.getCustomerCountByRegion(region);
    }

}
