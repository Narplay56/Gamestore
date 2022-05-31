package cat.uvic.teknos.m06.gamestore.domain.repositories;

import cat.uvic.teknos.m06.gamestore.domain.models.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeMethod;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class JpaEmployeeRepositoryTest {
    private static EntityManagerFactory entityManagerFactory;
    private static Repository <Employee, Integer> repository;

    @BeforeAll
    static void setUp() {
       entityManagerFactory = Persistence.createEntityManagerFactory("gamestart-mysql");
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
        employee.setName("Johan Liebheart");
        employee.setWorkShift("N");


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