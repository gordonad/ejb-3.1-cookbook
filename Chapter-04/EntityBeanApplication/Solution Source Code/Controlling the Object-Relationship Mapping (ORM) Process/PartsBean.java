package packt;

import java.awt.Color;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PARTS")
public class PartsBean implements Serializable {

    @Column(name="NAME")
    private String name;

    @Column(name="PARTNUMBER")
    private int partNumber;

    @Column(name="WEIGHT")
    private float weight;

    @Column(name="QUANTITY")
    private int quantity;

    public enum ColorEnumeration {RED, GREEN, BLUE}
    @ElementCollection
    private Set<ColorEnumeration> colors;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<ColorEnumeration> getColors() {
        return colors;
    }

    public void setColors(Set<ColorEnumeration> colors) {
        this.colors = colors;
    }
   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PartsBean)) {
            return false;
        }
        PartsBean other = (PartsBean) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "packt.PartsBean[id=" + id + "]";
    }

}
