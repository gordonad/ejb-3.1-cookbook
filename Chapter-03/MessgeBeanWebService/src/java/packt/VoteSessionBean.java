/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package packt;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;

/**
 *
 * @author Richard
 */
@Stateless
@LocalBean
public class VoteSessionBean {
    private String comedian;
    @EJB
    VoteBean vote;

    @Resource(mappedName="jms/VoteQueueFactory")
    private QueueConnectionFactory queueConnectionFactory;
    @Resource(mappedName="jms/VoteQueue")
    private Queue queue;

    public String getComedian() {
        return comedian;
    }

    public void setComedian(String comedian) {
        this.comedian = comedian;

                    Connection connection;

                try {
                connection = queueConnectionFactory.createConnection();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer messageProducer = (MessageProducer) session.createProducer(queue);
//                ObjectMessage commentMessage = session.createObjectMessage(comment);
//                commentMessage.setStringProperty("test", "tested");
//                messageProducer.send(commentMessage);
                System.out.println("---> vote sent");
            } catch (JMSException ex) {
                //Logger.getLogger(CommentServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
}


 
}
