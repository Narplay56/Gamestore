package cat.uvic.teknos.m06.gamestore.domain.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private int CustomerId;
    private String Name;
    private String Email;
    private int Postcode;
    @Transient
    private int EmpID;

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getPostcode() {
        return Postcode;
    }

    public void setPostcode(int postcode) {
        Postcode = postcode;
    }

    public int getEmpID() {
        return EmpID;
    }

    public void setEmpID(int empID) {
        EmpID = empID;
    }
}