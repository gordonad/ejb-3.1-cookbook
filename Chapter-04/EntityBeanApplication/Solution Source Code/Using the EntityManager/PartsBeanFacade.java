package packt;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PartsBeanFacade extends AbstractFacade<PartsBean> {
    @PersistenceContext(unitName = "EntityBeanApplication-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public PartsBeanFacade() {
        super(PartsBean.class);
    }

}
