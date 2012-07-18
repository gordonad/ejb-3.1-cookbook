package packt;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class LicenseBeanFacade extends AbstractFacade<LicenseBean> {
    @PersistenceContext(unitName = "ValidationApplication-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public LicenseBeanFacade() {
        super(LicenseBean.class);
    }

}
