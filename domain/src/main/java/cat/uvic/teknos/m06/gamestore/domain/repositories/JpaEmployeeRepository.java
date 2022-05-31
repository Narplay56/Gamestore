package cat.uvic.teknos.m06.gamestore.domain.repositories;

import cat.uvic.teknos.m06.gamestore.domain.models.Employee;
import org.testng.annotations.Factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class JpaEmployeeRepository implements EmployeeRepository {
    private final EntityManagerFactory entityManagerFactory;

    public JpaEmployeeRepository(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<Employee> getByEmployee (Employee employee) {
        return null;
    }

    @Override
    public void save(Employee employee) {
        if (employee.getEmpId() <= 0){
            insert(employee);
        }
        else{
            update(employee);
        }
    }
    private void insert(Employee employee){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
    }
    private void update(Employee employee){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(employee);
        entityManager.getTransaction().commit();
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
