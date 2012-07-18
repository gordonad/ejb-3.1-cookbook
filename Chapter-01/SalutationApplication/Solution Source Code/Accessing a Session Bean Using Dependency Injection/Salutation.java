package packt;
import javax.ejb.Stateless;

@Stateless
public class Salutation {
    public String getFormalSalutation(String name) {
        return "Dear " + name;
    }

    public String getInformalSalutation(String name) {
        return "Hi " + name;
    }
}
