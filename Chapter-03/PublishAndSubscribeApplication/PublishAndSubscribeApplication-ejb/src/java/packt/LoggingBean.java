package packt;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(mappedName = "jms/AvailabilityTopic", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
    @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "LoggingBean"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "LoggingBean")
})
public class LoggingBean implements MessageListener {

    public LoggingBean() {
    }

    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
            Availability availability = (Availability) objectMessage.getObject();
            if(availability.isAvailable()) {
                Logger.getLogger(LoggingBean.class.getName()).log(Level.SEVERE,
                        availability.getName() + " is available");
            } else {
                Logger.getLogger(LoggingBean.class.getName()).log(Level.SEVERE,
                        availability.getName() + " is not available");
            }
            System.out.println("---> logging ");
        } catch (JMSException ex) {
            Logger.getLogger(LoggingBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
