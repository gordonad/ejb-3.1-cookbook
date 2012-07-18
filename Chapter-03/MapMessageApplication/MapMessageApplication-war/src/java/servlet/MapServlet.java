package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MapServlet extends HttpServlet {

    @Resource(mappedName="jms/MapFactory")
    private QueueConnectionFactory queueConnectionFactory;
    @Resource(mappedName="jms/MapQueue")
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
                MapMessage mapMessage = session.createMapMessage();
                mapMessage.setInt("PartNumber",12345);
                mapMessage.setFloat("Weight",12.5f);
                mapMessage.setInt("Quantity",50);
                messageProducer.send(mapMessage);
                System.out.println("---> mapMessage sent " + mapMessage.getInt("PartNumber"));
            } catch (JMSException ex) {
                Logger.getLogger(MapServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MapServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MapServlet at " + request.getContextPath () + "</h1>");
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
