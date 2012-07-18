package packt;

import java.util.HashMap;
import javax.ejb.Stateless;

@Stateless
public class CapitalBean implements CapitalBeanRemote {

    private HashMap<String, String> capitals = new HashMap<String, String>();

    public CapitalBean() {
        capitals.put("United Kingdom", "London");
        capitals.put("Japan", "Tokyo");
        capitals.put("India", "New Delhi");
    }

    @Override
    public String getCapital(String country) {
        return capitals.get(country);
    }
}
