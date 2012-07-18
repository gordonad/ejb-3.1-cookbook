package tip;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

@Path("tipoftheday")
@Stateless
public class TipOfTheDay {
    @EJB
    TipSessionBean tips;

    @GET
    @Produces("text/html")
    public String processGet() {
        return getTip();
    }

    @POST
    @Produces("text/html")
    public String processPost() {
        return getTip();
    }

    private String getTip() {
        return tips.getTip();
    }
   
}
