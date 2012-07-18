package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import packt.RegistrationBean;

public class RegistrationServlet extends HttpServlet {

//    @PersistenceUnit//(unitName = "ApplicationManagedPersistenceApplication-warPU", type = PersistenceContextType.EXTENDED)
    @PersistenceUnit(unitName = "ApplicationManagedPersistenceApplication-warPU")
    EntityManagerFactory entityManagerFactory;
    @Resource
    UserTransaction userTransaction;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RegistrationBean registration;
        RegistrationBean secondRegistration;
        EntityManager entityManager;

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            try {

                registration = new RegistrationBean();
                registration.setName("Steve Best");
                registration.setCompany("Grey Beard Software");
                registration.setSession(10);

                entityManager = entityManagerFactory.createEntityManager();
                entityManager.persist(registration);

                secondRegistration = entityManager.find(RegistrationBean.class, registration.getId());
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet RegistrationServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>" + secondRegistration.getName() + " ID: " +
                        secondRegistration.getId()+ "</h1>");
                out.println("</body>");
                out.println("</html>");
                entityManager.clear();

            } catch (SecurityException ex) {
                Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalStateException ex) {
                Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }


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
