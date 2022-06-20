package cat.uvic.teknos.m06.gamestore.domain.repositories;

import cat.uvic.teknos.m06.gamestore.domain.models.Employee;

import java.util.List;

public interface EmployeeRepository extends Repository<Employee, Integer>{
    List<Employee> getByEmployee (Employee employee);
}
