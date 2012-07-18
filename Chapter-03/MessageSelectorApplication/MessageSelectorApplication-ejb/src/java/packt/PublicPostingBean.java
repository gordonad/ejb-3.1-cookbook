package packt;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(mappedName = "jms/PostingsQueue", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "PostingType = 'public'")
    })
public class PublicPostingBean implements MessageListener {
    
    public PublicPostingBean() {
    }

    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage)message;
        try {
            System.out.println("Public Post Received - " + textMessage.getText());
        } catch (JMSException ex) {
            Logger.getLogger(PublicPostingBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
