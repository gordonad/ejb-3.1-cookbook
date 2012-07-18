package packt;

import javax.ejb.Stateless;

@Stateless
public class CustomerManager {
    
    public int getCustomerCount() {
        return 27;
    }

    public int getCustomerCountByRegion(String region) {
        if("West".equals(region)) {
            return 12;
        } else  if("East".equals(region)) {
            return 15;
        } else {
            return 0;
        }
    }
}
