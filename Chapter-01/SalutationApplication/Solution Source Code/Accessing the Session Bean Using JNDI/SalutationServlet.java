package servlet;
import packt.Salutation;

@WebServlet(urlPatterns = {"/SalutationServlet"})
public class SalutationServlet extends HttpServlet {

    private Salutation salutation;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();        Context context = null;
        try {
            context = new InitialContext();
            salutation = (Salutation) context.lookup(
                  "java:global/SalutationApplication/SalutationApplication-ejb/Salutation");
        } catch (Exception e) {
            e.printStackTrace();
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
