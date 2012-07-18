package packt;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(mappedName = "jms/Customer", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class AddMessage implements MessageListener {

    public AddMessage() {
    }

    public void onMessage(Message message) {
        System.out.print("onMessage");
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println(textMessage.getText());
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }

    }
}
