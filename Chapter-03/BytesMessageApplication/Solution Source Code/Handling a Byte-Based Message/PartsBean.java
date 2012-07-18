package packt;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName = "jms/PartsQueue", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    })
public class PartsBean implements MessageListener {
    
    public PartsBean() {
    }

    public void onMessage(Message message) {
        BytesMessage bytesMessage = (BytesMessage) message;
        try {
            System.out.println("Part Numer: " + bytesMessage.readInt());
            System.out.println("Weight: " + bytesMessage.readFloat());
            System.out.println("Quantity: " + bytesMessage.readInt());
        } catch (JMSException ex) {
            Logger.getLogger(PartsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("---> parts received");
    }
    
}
