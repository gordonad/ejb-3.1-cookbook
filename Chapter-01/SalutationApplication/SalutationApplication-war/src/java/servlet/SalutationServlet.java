package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import packt.Salutation;

@WebServlet(urlPatterns = {"/SalutationServlet"})
public class SalutationServlet extends HttpServlet {

    //@EJB
    //@Resource
    private Salutation salutation;
    @Resource(mappedName = "jms/SalutationQueueFactory")
    private QueueConnectionFactory queueConnectionFactory;
    @Resource(mappedName = "jms/SalutationQueue")
    private Queue queue;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Context context = null;
        try {
            context = new InitialContext();
            salutation = (Salutation) context.lookup(
                    "java:global/SalutationApplication/SalutationApplication-ejb/Salutation");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String message = "Salutation generated";

            Connection connection = queueConnectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = (MessageProducer) session.createProducer(queue);
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText(message);
            messageProducer.send(textMessage);
            Logger.getLogger("SalutationLog").log(Level.WARNING,
                    "Message sent successfully", "Message sent successfully2");
        } catch (JMSException ex) {
            Logger.getLogger("SalutationLog").log(Level.WARNING,
                    "JMSException in SalutationServlet", "JMSException in SalutationServlet");
        }

        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SalutationServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + salutation.getFormalSalutation("Sherlock Holmes") + "</h1>");
            out.println("<h2>" + salutation.getContextInformation() + "</h2>");
//            out.println("<h2>" + salutation.getContextInformationJNDI() + "</h2>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.flush();
            out.close();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}