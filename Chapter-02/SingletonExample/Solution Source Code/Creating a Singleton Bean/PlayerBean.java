package packt;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;

@Singleton
@LocalBean
public class PlayerBean {
    private String name;

    @PostConstruct
    private void initialize() {
        this.name = "Ultimate Software Warrior";
        // Initialize player files
        System.out.println("PlayerBean initialized " + this.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
