package packt;


import java.io.PrintWriter;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class MedicationFacade extends AbstractFacade<Medication> {

    @PersistenceContext(unitName="PatientApplication-ejbPU")
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public MedicationFacade() {
        super(Medication.class);
    }

    public List<String> findByType() {
        Query query = entityManager.createQuery("SELECT m.name FROM Medication m WHERE m.type = 'ACE'");
        List<String> list = query.getResultList();
        return list;
    }

    public void displayAll(PrintWriter out) {
        Query query = entityManager.createQuery("SELECT m FROM Medication m WHERE m.dosage = 10");
        List<Medication> list = query.getResultList();
        for (Medication medication : list) {
            out.println(
                    "<h5>Medication ID: " + medication.getId() +
                    " Name: " + medication.getName() +
                    " Type: " + medication.getType() +
                    " Dosage: " + medication.getDosage() +
                    " Frequency: " + medication.getFrequency()+ "</h5>");
        }
    }

}
