/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package packt;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Named;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author Richard
 */
@MessageDriven(mappedName = "jms/VoteService", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    })
public class VoteBean implements MessageListener {
    
    public VoteBean() {
    }

    public void onMessage(Message message) {
        System.out.println("--->Vote onMessage executed");
    }
    
}
