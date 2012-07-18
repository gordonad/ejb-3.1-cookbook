package packt;

import java.io.Serializable;

public class Availability implements Serializable {
    private String name;
    private boolean available;

    public Availability(String name, boolean available) {
        this.name = name;
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getName() {
        return name;
    }
    
}
