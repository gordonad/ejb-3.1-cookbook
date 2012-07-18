package packt;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.StreamMessage;

@MessageDriven(mappedName = "jms/ItemsQueue", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    })
public class ItemBean implements MessageListener {
    
    public ItemBean() {
    }

    public void onMessage(Message message) {
        StreamMessage streamMessage = (StreamMessage) message;
        try {
            System.out.println("Part Number: " + streamMessage.readInt());
            System.out.println("Weight: " + streamMessage.readFloat());
            System.out.println("Quantity: " + streamMessage.readInt());
        } catch (JMSException ex) {
            Logger.getLogger(ItemBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("---> Item received");
    }
    
}
