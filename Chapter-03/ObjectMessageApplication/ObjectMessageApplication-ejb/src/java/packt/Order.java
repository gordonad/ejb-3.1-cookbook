package packt;

import java.io.Serializable;

public class Order implements Serializable {
    private int partNumber;
    private float weight;
    private int quantity;

    public Order(int partNumber, float weight, int quantity) {
        this.partNumber = partNumber;
        this.weight = weight;
        this.quantity = quantity;
    }

    public int getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
    
}
