package jaxrs;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import packt.CustomerManager;

@Path("customer")
@Stateless
public class Customer {

    @EJB
    private CustomerManager customerManager;

    @Resource(mappedName="jms/CustomerFactory")
    private QueueConnectionFactory queueConnectionFactory;

    @Resource(mappedName="jms/Customer")
    private Queue queue;
    
    @GET
    @Produces("text/html")
    public String doGet() {
        return "<h3>Customer Count: " + customerManager.getCustomerCount() + "</h3>";
    }

    @POST
    @Produces("text/html")
    @Consumes("application/x-www-form-urlencoded")
    public String doPost(@FormParam("region") String region) {
        try {
            String message = region + " passed";

            Connection connection = queueConnectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = (MessageProducer) session.createProducer(queue);
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText(message);
            messageProducer.send(textMessage);
            System.out.println("Message sent successfully");
        } catch (JMSException ex) {
            System.out.println("JMSException in SalutationServlet");
        }
        return "<h3>Customer Count: "
                + customerManager.getCustomerCountByRegion(region) + "</h3>";
    }

}
