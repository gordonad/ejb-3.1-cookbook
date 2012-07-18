package packt;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(mappedName = "jms/TextQueue", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    })
public class TextBean implements MessageListener {
    
    public TextBean() {
    }

    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            Scanner scanner = new Scanner(textMessage.getText());
            System.out.println("---> Part Number: " + scanner.nextInt());
            System.out.println("---> Weight: " + scanner.nextFloat());
            System.out.println("---> Quantity: " + scanner.nextInt());
            System.out.println("---> TextMessage Received");
        } catch (JMSException ex) {
            Logger.getLogger(TextBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
