package cat.uvic.teknos.m06.gamestore.domain.repositories;

import cat.uvic.teknos.m06.gamestore.domain.models.Employee;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeMethod;

import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class JpaEmployeeRepositoryTest {
    @BeforeMethod
    public void setUp() {

    }
    @Test
    void  save(){
        var entityManagerFactory = Persistence.createEntityManagerFactory("gamestart-mysql");
        JpaEmployeeRepository repository = new JpaEmployeeRepository(entityManagerFactory);

        Employee employee = new Employee();
        employee.setName("Anna Liebheart");
        employee.setWorkShift("M");


        assertDoesNotThrow(() ->{
           repository.save(employee);
        });

        assertTrue(employee.getEmpId() > 0);

    }
    @Test
    void delete(){

    }
    @Test
    void getByid(){}

    @Test
    void getAll(){}
}