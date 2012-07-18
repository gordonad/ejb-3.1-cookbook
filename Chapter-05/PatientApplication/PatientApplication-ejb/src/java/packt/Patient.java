package packt;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="PATIENT")
public class Patient implements Serializable {
    @Column(name="FIRSTNAME")
    private String firstName;
    @Column(name="LASTNAME")
    private String lastName;
    @Column(name="SEX")
    private char sex;
    @Column(name="DATEOFBIRTH")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBirth;
    @OneToMany(mappedBy="patient")
    private Collection<Medication> medications;

    public Patient() {

    }

    public Collection<Medication> getMedications() {
        return medications;
    }

    public void setMedications(Collection<Medication> medications) {
        this.medications = medications;
    }

    public void addMedication(Medication medication) {
        medications.add(medication);
    }

    public Medication removeMedication(Medication medication) {
        medications.remove(medication);
        medication.setPatient(null);
        return medication;
    }

    public Patient(String firstName, String lastName, char sex, Date dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.dateOfBirth = dob;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
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
        if (!(object instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "packt.PatientEntity[id=" + id + "]";
    }

}