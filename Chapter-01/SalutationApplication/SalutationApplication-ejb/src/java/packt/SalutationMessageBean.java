package packt;

import java.util.logging.Level;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.logging.Logger;
import javax.jms.JMSException;

@MessageDriven(mappedName = "jms/SalutationQueue", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    })
public class SalutationMessageBean implements MessageListener {

    public SalutationMessageBean() {
    }

    public void onMessage(Message message) {
        try {
         String name = message.getStringProperty("name");
         Logger.getLogger("SalutationLog").log(Level.INFO,
                 "Salutation processed", "Test of log3");
         } catch (JMSException e) {
             throw new RuntimeException(e);
         }
    }

}