package jaxrs;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import packt.CustomerManager;

@Path("customer")
@Stateless
public class Customer {
    @EJB
    private CustomerManager customerManager;

    @GET
    @Produces("text/html")
    public String doGet() {
        return "<h3>Customer Count: " + customerManager.getCustomerCount() + "</h3>";
    }

    @POST
    @Produces("text/html")
    @Consumes("application/x-www-form-urlencoded")
    public String doPost(@FormParam("region") String region) {
        return "<h3>Customer Count: "
                + customerManager.getCustomerCountByRegion(region) + "</h3>";
    }

}

