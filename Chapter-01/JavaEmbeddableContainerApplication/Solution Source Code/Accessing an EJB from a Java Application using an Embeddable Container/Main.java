package embeddablecontainerapp;

import java.util.HashMap;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import packt.CapitalBeanRemote;

public class Main {

    public static void main(String[] args) {
         try {
            Map properties = new HashMap();

            properties.put(EJBContainer.MODULES,new java.io.File(
       "E:\\Packt\\Projects\\Chapter 1\\CapitalApplication\\build\\classes"));
            System.out.println("properties: " + properties);
            EJBContainer ejbC = EJBContainer.createEJBContainer(properties);
            Context context = ejbC.getContext();

            String name = "java:global/CapitalApplication/CapitalBean";
            CapitalBeanRemote bean = (CapitalBeanRemote)context.lookup(name);
            System.out.println(bean.getCapital("Japan"));

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

}
