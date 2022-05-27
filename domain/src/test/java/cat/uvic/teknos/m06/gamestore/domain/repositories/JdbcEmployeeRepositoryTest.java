package cat.uvic.teknos.m06.gamestore.domain.repositories;

import cat.uvic.teknos.m06.gamestore.domain.models.Employee;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class JdbcEmployeeRepositoryTest {

    @Test
    void save() throws SQLException {
        var connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamestore", "root", null);
        var jdbcEmployeeRepository = new JdbcEmployeeRepository(connection);
        Employee employee = new Employee();
        employee.setName("Matias Lazo");
        employee.setWorkShift("N");
        jdbcEmployeeRepository.save(employee);
//        jdbcEmployeeRepository.getAll();
//        employee.setName("Anna Pache");
//        employee.setEmpId(9);
//        employee.setWorkShift("M");
//        jdbcEmployeeRepository.save(employee);
//        Employee employee = jdbcEmployeeRepository.getById(9);
//        System.out.println(employee.getName()
//        );
        // jdbcEmployeeRepository.delete(employee);
    }

    @Test
    void saveUp() throws SQLException {
        var connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamestore", "root", null);
        var jdbcEmployeeRepository = new JdbcEmployeeRepository(connection);
        Employee employee = new Employee();
        employee.setName("Johan Liebheart");
        employee.setWorkShift("M");
        employee.setEmpId(1);
        jdbcEmployeeRepository.save(employee);

    }

    @Test
    void Delete()throws SQLException {
        var connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamestore", "root", null);
        var jdbcEmployeeRepository = new JdbcEmployeeRepository(connection);
        Employee employee = new Employee();
        employee.setEmpId(2);
        jdbcEmployeeRepository.delete(employee);

    }
}