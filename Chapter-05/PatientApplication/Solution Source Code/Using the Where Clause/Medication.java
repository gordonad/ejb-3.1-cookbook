package packt;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "MEDICATION")
@NamedQueries({
    @NamedQuery(name = "findByType",
    query = "SELECT m FROM Medication m WHERE m.type = ?1")
//            ,
//    @NamedQuery(name = "deleteByType",
//    query = "DELETE m FROM Medication m WHERE m.type = ?1")
})
public class Medication implements Serializable {

    @ManyToOne
    private Patient patient;

    @Column(name = "NAME")
    private String name;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "DOSAGE")
    private int dosage;
    @Column(name = "FREQUENCY")
    private int frequency;

    public Medication() {
    }

    public Medication(String medication, String type, int dosage, int frequency) {
        this.name = medication;
        this.type = type;
        this.dosage = dosage;
        this.frequency = frequency;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Medication)) {
            return false;
        }
        Medication other = (Medication) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "packt.MedicationBean[id=" + id + "]";
    }
}

