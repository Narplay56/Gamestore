package cat.uvic.teknos.m06.gamestore.domain.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity

public class Employee {
    @Id
    @GeneratedValue
    private int EmpId;
    private String name;
    private String WorkShift;

    public int getEmpId() {
        return EmpId;
    }

    public void setEmpId(int empId) {
        EmpId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkShift() {
        return WorkShift;
    }

    public void setWorkShift(String workShift) {
        WorkShift = workShift;
    }
}
