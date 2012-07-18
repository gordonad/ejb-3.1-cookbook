package packt;

import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CityFacade extends AbstractFacade<City> {

    @PersistenceContext(unitName = "PopulationApplication-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public void create(City entity) {
        getEntityManager().persist(entity);
    }

    public void changePopulation(String cityName, long count) {
        System.out.println("Executing changePopulation");
        Query query = em.createQuery(
                "UPDATE City c "
                + "SET c.population = c.population+:count "
                + "WHERE c.name = :cityName");
        query.setParameter("count", count);
        query.setParameter("cityName", cityName);
        int result = query.executeUpdate();
        System.out.println("result: " + result);
        System.out.println("--- end changePopulation");
    }

    public CityFacade() {
        super(City.class);
    }

}
