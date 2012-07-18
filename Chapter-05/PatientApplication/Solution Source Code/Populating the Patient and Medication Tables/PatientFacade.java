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

}
