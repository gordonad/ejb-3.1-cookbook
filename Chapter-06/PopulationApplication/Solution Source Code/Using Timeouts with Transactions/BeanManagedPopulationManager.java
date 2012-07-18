package packt;

import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@Stateful
@TransactionManagement(TransactionManagementType.BEAN)
public class BeanManagedPopulationManager {

    @Resource
    private UserTransaction userTransaction;

    @PersistenceContext(unitName = "PopulationApplication-ejbPU")
    private EntityManager em;

    public void changePopulation(String cityName, long count) throws SystemException {
        try {
            System.out.println("Executing changePopulation");
            userTransaction.setTransactionTimeout(10);
            userTransaction.begin();
            System.out.println("Transaction State: " +
                    getTransactionStateString(userTransaction.getStatus()));
            Thread.sleep(20000);
            Query query = em.createQuery(
                    "UPDATE City c "
                    + "SET c.population = c.population+:count "
                    + "WHERE c.name = :cityName");
            query.setParameter("count", count);
            query.setParameter("cityName", cityName);
            int result = query.executeUpdate();
            userTransaction.commit();
            System.out.println("result: " + result);
            System.out.println("--- end changePopulation");
        } catch (Exception e) {
            System.out.println("Transaction State: " +
                    getTransactionStateString(userTransaction.getStatus()));
        }
    }


    private String getTransactionStateString(int state) {
        switch (state) {
            case Status.STATUS_ACTIVE:
                return "STATUS_ACTIVE: The transaction is active";

            case Status.STATUS_COMMITTED:
                return "STATUS_COMMITTED: The transaction has been committed";

            case Status.STATUS_COMMITTING:
                return "STATUS_COMMITTING: The transaction is being committed";

            case Status.STATUS_MARKED_ROLLBACK:
                return "STATUS_MARKED_ROLLBACK: The transaction is marked for rollback";

            case Status.STATUS_NO_TRANSACTION:
                return "STATUS_NO_TRANSACTION: There is not transaction";

            case Status.STATUS_PREPARED:
                return "STATUS_PREPARED: The transaction is in a prepared state, ready to commit";

            case Status.STATUS_PREPARING:
                return "STATUS_PREPARING: The transaction is preparing to commit";

            case Status.STATUS_ROLLEDBACK:
                return "STATUS_ROLLEDBACK: The transaction has been rollbacked";

            case Status.STATUS_ROLLING_BACK:
                return "STATUS_ROLLING_BACK: The transaction is being rollbacked";

            case Status.STATUS_UNKNOWN:
                return "STATUS_UNKNOWN: The transaction is in a unknown state";

            default:
                return "Status is not available";
        }
    }
}
