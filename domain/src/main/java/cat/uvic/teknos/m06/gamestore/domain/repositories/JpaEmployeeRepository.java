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
    public void delete(Integer id) {
        var entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        var employee = entityManager.find(Employee.class, id);
        if (employee != null) {
            entityManager.remove(employee);
        }
        entityManager.getTransaction().commit();

    }

    @Override
    public Employee getById(Integer id) {
        var entityManager = entityManagerFactory.createEntityManager();
        var employee = entityManager.find(Employee.class, id);
        entityManager.close();

        return employee;
    }

    @Override
    public List<Employee> getAll() {
        var entityManager = entityManagerFactory.createEntityManager();
        var query = entityManager.createQuery("SELECT employee FROM Employee employee");
        return query.getResultList();
    }
}
