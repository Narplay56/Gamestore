package cat.uvic.teknos.m06.gamestore.domain.models;

public class Customer {
    private int CustomerId;
    private String Email;
    private int Postcode;
    private int EmpID;

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
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
