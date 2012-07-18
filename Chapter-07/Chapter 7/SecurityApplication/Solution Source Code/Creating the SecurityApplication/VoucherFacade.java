package packt;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
public class VoucherFacade extends AbstractFacade<Voucher> {
    @PersistenceContext(unitName = "SecurityApplication-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public VoucherFacade() {
        super(Voucher.class);
    }

}
