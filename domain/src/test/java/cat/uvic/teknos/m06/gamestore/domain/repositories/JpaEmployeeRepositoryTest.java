package cat.uvic.teknos.m06.gamestore.domain.repositories;

import cat.uvic.teknos.m06.gamestore.domain.models.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeMethod;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class JpaEmployeeRepositoryTest {
    public static final int MODEL_TO_DELETE = 2;
    private static EntityManagerFactory entityManagerFactory;
    private static Repository <Employee, Integer> repository;

    @BeforeAll
    static void setUp() {
       entityManagerFactory = Persistence.createEntityManagerFactory("gamestart-mysql");
       repository = new JpaEmployeeRepository(entityManagerFactory);

    }
    @Test
    void  save(){
        Employee employee = new Employee();
        employee.setName("Anna Liebheart");
        employee.setWorkShift("M");


        assertDoesNotThrow(() ->{
           repository.save(employee);
        });

        assertTrue(employee.getEmpId() > 0);

    }
    @Test
    void  saveUpdate(){
        Employee employee = new Employee();
        employee.setEmpId(1);
        employee.setName("Johan Liebheart");
        employee.setWorkShift("N");


        assertDoesNotThrow(() ->{
           repository.save(employee);
        });

        assertTrue(employee.getEmpId() > 0);

        var entityManager = entityManagerFactory.createEntityManager();
        var modifiedEmployee = entityManager.find(Employee.class,1 );

        assertEquals("Johan Liebheart", modifiedEmployee.getName());
        assertEquals("N", modifiedEmployee.getWorkShift());
        entityManager.close();

    }
    @Test
    void delete(){
        var pere = repository.getById(MODEL_TO_DELETE);

        assertNotNull(pere);

        assertDoesNotThrow(() -> {
            repository.delete(MODEL_TO_DELETE);
        });

        pere = repository.getById(MODEL_TO_DELETE);

        assertNull(pere);

    }
    @Test
    void getByid(){
            var employee = repository.getById(1);

            assertNotNull(employee);
    }

    @Test
    void getAll(){
        var employees = repository.getAll();

        assertNotNull(employees);
        assertTrue(employees.size() > 0);
    }
}