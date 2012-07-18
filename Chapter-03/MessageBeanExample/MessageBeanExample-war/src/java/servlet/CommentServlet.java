package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import packt.Comment;

public class CommentServlet extends HttpServlet {
   
    private Comment comment;
    @Resource(mappedName="jms/CommentsQueueFactory")
    private QueueConnectionFactory queueConnectionFactory;
    @Resource(mappedName="jms/CommentsQueue")
    private Queue queue;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            Connection connection;
            comment = new Comment(true,"Its the greatest thing since sliced bread!",
                    Calendar.getInstance().getTime());

            try {
                connection = queueConnectionFactory.createConnection();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer messageProducer = (MessageProducer) session.createProducer(queue);
                ObjectMessage commentMessage = session.createObjectMessage(comment);
                commentMessage.setStringProperty("test", "tested");
                messageProducer.send(commentMessage);
                System.out.println("---> comment sent");
            } catch (JMSException ex) {
                Logger.getLogger(CommentServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CommentServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CommentServlet!!! at " + request.getContextPath () + "</h1>");
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
