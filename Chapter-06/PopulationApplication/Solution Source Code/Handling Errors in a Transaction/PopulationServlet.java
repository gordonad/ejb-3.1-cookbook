package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import packt.BeanManagedPopulationManager;
import packt.City;
import packt.CityFacade;
import packt.PopulationManager;

public class PopulationServlet extends HttpServlet {

    @EJB
    CityFacade cityFacade;
    @EJB
    PopulationManager populationManager;
    @EJB
    BeanManagedPopulationManager bean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            clearTables();
            populationManager.addCity("Tokyo", "Japan", 32450000);
            populationManager.updatePopulation("Tokyo", -1000);
            List<City> cities = cityFacade.findAll();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PopulationServlet</title>");
            out.println("</head>");
            out.println("<body>");
            for (City c : cities) {
                out.println("<h5>Rio: " + c.getName() + " - " + c.getPopulation() + "</h5>");
            }

            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    private void clearTables() {
        List<City> cities = cityFacade.findAll();
        for (City c : cities) {
            cityFacade.remove(c);
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
