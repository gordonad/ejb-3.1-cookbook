package servlet;
import packt.Salutation;

@WebServlet(urlPatterns = {"/SalutationServlet"})
public class SalutationServlet extends HttpServlet {

    @Resource(mappedName = "jms/SalutationQueueFactory")
    private QueueConnectionFactory queueConnectionFactory;
    @Resource(mappedName = "jms/SalutationQueue")
    private Queue queue;

    private Salutation salutation;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();        
       Context context = null;

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
