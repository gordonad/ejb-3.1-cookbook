package packt;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

@Stateless
public class PatientFacade extends AbstractFacade<Patient> {

    @PersistenceContext(unitName = "PatientApplication-ejbPU")
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public PatientFacade() {
        super(Patient.class);
    }

    @Override
    public List<Patient> findAll() {
        Query query = entityManager.createQuery("select p FROM Patient p");
        List<Patient> list = query.getResultList();
        return list;
    }

    public void processAllPatients(PrintWriter out) {
        int querySize = 5;
        int beginIndex = 0;
        while(true) {
            Query query = entityManager.createQuery("SELECT p FROM Patient p");
            query.setMaxResults(querySize);
            query.setFirstResult(beginIndex);
            List<Patient> list = query.getResultList();
				// Process list
            if (list.isEmpty()) {
                break;
            } else {
                // Process
                for (Patient patient : list) {
                    out.println("<h5>"+patient.getFirstName()+"</h5>");
                }
                entityManager.clear();
                beginIndex = beginIndex + list.size();
            }
        }
    }

}
