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

}
