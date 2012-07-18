package packt;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;

@MessageDriven(mappedName = "jms/OrderQueue", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    })
public class OrderBean implements MessageListener {

    // These declarations are used to send out a thank you order
    @Resource(mappedName="jms/OrderFactoryPool")
    private QueueConnectionFactory queueConnectionFactory;
    @Resource(mappedName="jms/OrderQueue")
    private Queue queue;

    public OrderBean() {
    }

    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage objectMessage = (ObjectMessage) message;
            Order order = (Order)objectMessage.getObject();
            System.out.println("Part Number: " + order.getPartNumber());
            System.out.println("Weight: " + order.getWeight());
            System.out.println("Quantity: " + order.getQuantity());
            System.out.println("Order Received");

            // Send out a thank you order
            if(order.getQuantity() > 40) {
                Connection connection;

                try {
                    connection = queueConnectionFactory.createConnection();
                    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                    MessageProducer messageProducer = (MessageProducer) session.createProducer(queue);
                    objectMessage = session.createObjectMessage();
                    objectMessage.setObject(new Order(54321,5.5f,1));
                    messageProducer.send(objectMessage);
                    System.out.println("---> Thank you order sent ");
                } catch (JMSException ex) {
                    Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (JMSException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    
}
