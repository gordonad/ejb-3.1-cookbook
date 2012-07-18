package packt;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Stateless
public class Salutation {

    @Resource(name = "sessionContext")
    private SessionContext context;

    public String getFormalSalutation(String name) {
        return "Dear " + name;
    }

    public String getInformalSalutation(String name) {
        return "Hi " + name;
    }

    public String getContextInformationJNDI() {
        SessionContext sctxLookup;
        try {
            InitialContext ic = new InitialContext();
            sctxLookup =
                    (SessionContext) ic.lookup("java:comp/EJBContext");
        } catch (NamingException ex) {
            return "NamingException: " + ex.toString();
            //throw new IllegalStateException(ex);
        }
        return sctxLookup.toString() + "<br/>" +
               sctxLookup.getInvokedBusinessInterface().toString() + "<br/>";
    }

    public String getContextInformation() {


        StringBuilder contextInformation = new StringBuilder();
        contextInformation.append(context.toString() + "<br/>");
        try {
            contextInformation.append(context.getInvokedBusinessInterface().toString() + "<br/>");
        } catch (IllegalStateException e) {
            contextInformation.append(e);
        }
        return contextInformation.toString();
    }
}
