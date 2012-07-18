package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import packt.Availability;

public class AvailabilityServlet extends HttpServlet {

    private Availability availability;
    @Resource(mappedName="jms/AvailabilityFactoryPool")
    private TopicConnectionFactory topicConnectionFactory;
    @Resource(mappedName="jms/AvailabilityTopic")
    private Topic topic;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            Connection connection;
            availability = new Availability("Tom",true);

            try {
                connection = topicConnectionFactory.createConnection();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer messageProducer = (MessageProducer) session.createProducer(topic);
                ObjectMessage availabilityMessage = session.createObjectMessage(availability);
                availabilityMessage.setStringProperty("test", "tested");
                messageProducer.send(availabilityMessage);
                System.out.println("---> availability status sent");
            } catch (JMSException ex) {
                Logger.getLogger(AvailabilityServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AvailabilityServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AvailabilityServlet at " + request.getContextPath () + "</h1>");
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
