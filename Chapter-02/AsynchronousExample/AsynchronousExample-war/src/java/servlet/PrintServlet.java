package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import packt.PrintBean;

public class PrintServlet extends HttpServlet {
   
    @EJB
    PrintBean printBean;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //printBean.setPrintID(10);
//            printBean.setText("Text to be printed");
//            printBean.setPrinter("Local Printer");
            printBean.printAndForget();
            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PrintServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>printAndForget executed</h3>");
            Future<String> futureResult = printBean.printAndCheckLater();
            String result = "";
            try {
                result = futureResult.get();
            } catch (InterruptedException ex) {
                Logger.getLogger(PrintServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(PrintServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("<h3>printAndCheckLater executed - Result: " + result + "</h3>");
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
