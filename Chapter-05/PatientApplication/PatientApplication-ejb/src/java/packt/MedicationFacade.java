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

    public int updateDosage(String type, int dosage) {
        Query query = entityManager.createQuery("UPDATE Medication m " +
                "SET m.dosage = " + dosage + " WHERE m.type = '" + type + "'");
        int numberUpdated = query.executeUpdate();
        return numberUpdated;
    }

    public List<Medication> findByType(String type) {
        Query query = entityManager.createNamedQuery("findByType");
        query.setParameter(1,type);
        return query.getResultList();
    }

    public List<String> findByType() {
        Query query = entityManager.createQuery("SELECT DISTINCT m.name FROM Medication m ORDER BY m.name");
        List<String> list = query.getResultList();
        return list;
    }

    public void displayAll(PrintWriter out) {
        Query query = entityManager.createQuery("SELECT m FROM Medication m WHERE m.type IN ('Anti-Fungal', 'Diuretic')");
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
