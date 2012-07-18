package packt;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class BinLocation implements Serializable {
    private int aisle;
    private int level;

    public int getAisle() {
        return aisle;
    }

    public void setAisle(int aisle) {
        this.aisle = aisle;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
}
