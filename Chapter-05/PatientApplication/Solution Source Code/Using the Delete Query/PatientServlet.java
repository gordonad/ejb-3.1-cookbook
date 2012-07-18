package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import packt.Medication;
import packt.MedicationFacade;
import packt.Patient;
import packt.PatientFacade;

public class PatientServlet extends HttpServlet {

    private Patient patient;
    @EJB
    private PatientFacade patientFacade;

    private Patient createPatient(String firstName, String lastName, char sex,
            int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        patient = new Patient(firstName, lastName, sex, calendar.getTime());
        patientFacade.create(patient);
        return patient;
    }
    private Medication medication;
    @EJB
    private MedicationFacade medicationFacade;

    private Medication createMedication(String name, String type,
            int dosage, int frequency) {
        Medication medication;
        medication = new Medication(name, type, dosage, frequency);
        medicationFacade.create(medication);
        return medication;
    }

    private void populateTables() {
        patient = createPatient("Donald", "Baker", 'M', 1976, 3, 13);
        patient.addMedication(createMedication("Accupril", "ACE", 10, 1));
        patient.addMedication(createMedication("Cleocin", "Anti-Bacterial", 2, 2));

        patient = createPatient("Jennifer", "Campbell", 'F', 1982, 5, 23);
        patient.addMedication(createMedication("Urex", "Anti-Bacterial", 5, 2));
        patient.addMedication(createMedication("Lasix", "Diuretic", 12, 1));

        patient = createPatient("Steven", "Young", 'M', 1965, 6, 12);
        patient.addMedication(createMedication("Vasitec", "ACE", 10, 2));

        patient = createPatient("George", "Thompson", 'M', 1957, 12, 2);
        patient.addMedication(createMedication("Altace", "ACE", 25, 1));
        patient.addMedication(createMedication("Amoxil", "Anti-Bacterial", 10, 4));
        patient.addMedication(createMedication("Mycelex", "Anti-Fungal", 12, 2));

        patient = createPatient("Sandra", "Taylor", 'F', 1998, 1, 23);
        patient.addMedication(createMedication("Accupril", "ACE", 10, 1));

        patient = createPatient("Maria", "Green", 'F', 1978, 7, 21);
        patient.addMedication(createMedication("Altace", "ACE", 25, 1));

        patient = createPatient("Sarah", "Walker", 'F', 1980, 10, 10);
        patient.addMedication(createMedication("Accupril", "ACE", 10, 1));
        patient.addMedication(createMedication("Ilosone", "Anti-Bacterial", 5, 2));
        patient.addMedication(createMedication("Terazol", "Anti-Fungal", 20, 1));
        patient.addMedication(createMedication("Aldactone", "Diuretic", 5, 3));

        patient = createPatient("Kevin", "Hall", 'M', 2005, 4, 2);

        patient = createPatient("Carol", "Harris", 'F', 1958, 8, 11);
        patient.addMedication(createMedication("Zyvox", "Anti-Bacterial", 10, 3));

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            // Remove all medications from the database
            List<Patient> patientList = patientFacade.findAll();
            for (Patient patient : patientList) {
                patientFacade.remove(patient);
            }

            // Remove all medications from the database
            List<Medication> medicationList = medicationFacade.findAll();
            for (Medication medication : medicationList) {
                medicationFacade.remove(medication);
            }

            // Populate the tables
            populateTables();

            // Display ...
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PatientServlet</title>");
            out.println("</head>");
            out.println("<body>");

            int numberDeleted = patientFacade.delete("Donald", "Baker");
            out.println("<h3>" + numberDeleted + " Entities deleted</h3>");

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
