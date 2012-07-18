package packt;

import java.rmi.RemoteException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PopulationManager
        implements SessionSynchronization {


    @EJB
    CityFacade cityFacade;

    public PopulationManager() {
     }

    public void addCity(String cityName, String county, long population) {
         City city = new City(cityName, county, population);
         cityFacade.create(city);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updatePopulation(String cityName, long count) {
        cityFacade.changePopulation(cityName, count);
    }

        @Override
    public void afterBegin() throws EJBException, RemoteException {
        System.out.println("\nPopulationManager afterBegin");
    }

    @Override
    public void beforeCompletion() throws EJBException, RemoteException {
        System.out.println("PopulationManager beforeCompletion");
    }

    @Override
    public void afterCompletion(boolean committed) throws EJBException, RemoteException {
        System.out.println("PopulationManager afterCompletion\n");
    }

}