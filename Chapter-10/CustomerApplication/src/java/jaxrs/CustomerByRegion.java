package jaxrs;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import packt.CustomerManager;

@Path("customerByRegion")
@Stateless
public class CustomerByRegion {

    @EJB
    private CustomerManager customerManager;

    @GET
    @Produces("text/html")
    public String doGet(
            @DefaultValue("East") @QueryParam("region") String region) {
        return "<h3>Customer Count: " + customerManager.getCustomerCountByRegion(region) + "</h3>";
    }
}
