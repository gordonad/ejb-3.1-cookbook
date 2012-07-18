package packt;

import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName = "jms/MapQueue", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    })
public class MapBean implements MessageListener {
    
    public MapBean() {
    }

    public void onMessage(Message message) {
        MapMessage mapMessage = (MapMessage) message;
        try {
            System.out.println("Part Number: " + mapMessage.getInt("PartNumber"));
            System.out.println("Weight: " + mapMessage.getFloat("Weight"));
            System.out.println("Quantity: " + mapMessage.getInt("Quantity"));

            System.out.println("List of key/value pairs");
            Enumeration enumeration = mapMessage.getMapNames();
            while(enumeration.hasMoreElements()) {
                String key = (String)enumeration.nextElement();
                System.out.println(key + ": " + mapMessage.getString(key));
            }
        } catch (JMSException ex) {
            Logger.getLogger(MapBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("---> map message received");
    }
    
}
