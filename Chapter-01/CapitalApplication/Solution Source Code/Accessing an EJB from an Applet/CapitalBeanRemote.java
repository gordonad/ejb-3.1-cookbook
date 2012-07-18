package packt;

import javax.ejb.Remote;

@Remote
public interface CapitalBeanRemote {
    public String getCapital(String country);
}
