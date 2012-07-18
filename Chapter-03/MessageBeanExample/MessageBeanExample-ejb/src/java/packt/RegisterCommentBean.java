package packt;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(mappedName = "jms/CommentsQueue", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "test = 'tested'")
    })
public class RegisterCommentBean implements MessageListener {
    
    public RegisterCommentBean() {
    }

    @PostConstruct
    private void initialize() {
        // Perform EJB initialization
        System.out.println("---> RegisterCommentBean initialization");
    }

    @PreDestroy
    private void terminate() {
        // Perform EJB termination
         System.out.println("---> RegisterCommentBean termination");
    }

    public void onMessage(Message message) {
        ObjectMessage commentMessage = (ObjectMessage)message;
        try {
            Comment comment = (Comment) commentMessage.getObject();
            System.out.println("---> comment received: " + comment.getComment()+
                    " "+commentMessage.getStringProperty("test"));
        } catch (JMSException ex) {
            Logger.getLogger(RegisterCommentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
