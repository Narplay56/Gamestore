package cat.uvic.teknos.m06.gamestore.domain.repositories;

import cat.uvic.teknos.m06.gamestore.domain.models.Employee;

import java.util.List;

public class JpaEmployeeRepository implements EmployeeRepository {

    @Override
    public List<Employee> getByEmployee (Employee employee) {
        return null;
    }

    @Override
    public void save(Employee model) {

    }

    @Override
    public void delete(Employee model) {

    }

    @Override
    public Employee getById(Integer id) {
        return null;
    }

    @Override
    public List<Employee> getAll() {
        return null;
    }
}
