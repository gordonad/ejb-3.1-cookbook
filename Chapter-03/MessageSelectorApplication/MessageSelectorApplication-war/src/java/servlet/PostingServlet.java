package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostingServlet extends HttpServlet {
   
    @Resource(mappedName="jms/PostingsQueueFactory")
    private QueueConnectionFactory queueConnectionFactory;
    @Resource(mappedName="jms/PostingsQueue")
    private Queue queue;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Connection connection;

            try {
                connection = queueConnectionFactory.createConnection();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer messageProducer = (MessageProducer) session.createProducer(queue);
                TextMessage textMessage = session.createTextMessage();

                textMessage.setText("For your eyes only");
                textMessage.setStringProperty("PostingType", "private");
                messageProducer.send(textMessage);
                System.out.println("---> Public textMessage sent");

                textMessage.setText("Distribute freely");
                textMessage.setStringProperty("PostingType", "public");
                messageProducer.send(textMessage);
                System.out.println("--->Private textMessage sent");

                // Used by Browsing Messages in a Message Queue recipe
                textMessage.setText("Distribute in house only");
                textMessage.setStringProperty("PostingType", "protected");
                messageProducer.send(textMessage);

                QueueBrowser queueBrowser = session.createBrowser(queue);
                Enumeration messages = queueBrowser.getEnumeration();
                while(messages.hasMoreElements()) {
                    TextMessage message = (TextMessage) messages.nextElement();
                    System.out.println("Message: " + message.getText());
                }

            } catch (JMSException ex) {
                Logger.getLogger(PostingServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PostingServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PostingServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
