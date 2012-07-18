package packtapp;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import packt.CapitalBeanRemote;

public class Main {

    public static void main(String[] args) {
        try {
            InitialContext context = new InitialContext();
            String name = "java:global/CapitalApplication/CapitalBean";
            CapitalBeanRemote bean = (CapitalBeanRemote)context.lookup(name);
            System.out.println(bean.getCapital("India"));
        } 
        catch(javax.naming.NoInitialContextException e) {
            e.printStackTrace();
        }
        catch (NamingException e) {
            e.printStackTrace();
        }
    }

}

