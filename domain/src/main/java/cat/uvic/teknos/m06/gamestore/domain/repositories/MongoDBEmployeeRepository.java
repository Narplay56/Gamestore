package cat.uvic.teknos.m06.gamestore.domain.repositories;


import cat.uvic.teknos.m06.gamestore.domain.models.Employee;

import java.util.List;

public class MongoDBEmployeeRepository implements EmployeeRepository {
    @Override
    public List<Employee> getByEmployee(Employee employee) {
        return null;
    }

    @Override
    public void save(Employee model) {

    }

    @Override
    public void delete(Integer id) {

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
