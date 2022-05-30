package cat.uvic.teknos.m06.gamestore.domain.repositories;

import cat.uvic.teknos.m06.gamestore.domain.models.Employee;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class JdbcEmployeeRepositoryTest {

    @Test
    void saveInsert() throws SQLException {
        var connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamestore","root", null);
        var jdbcEmployeeRepository = new JdbcEmployeeRepository(connection);
        Employee employee = new Employee();
        employee.setName("Matias Lazo");
        employee.setWorkShift("N");
        jdbcEmployeeRepository.save(employee);
    }
    @Test
    void saveUpdate() throws SQLException {
        var connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamestore","root", null);
        var jdbcEmployeeRepository = new JdbcEmployeeRepository(connection);
        Employee employee = new Employee();
        employee.setName("Johan Liebheart");
        employee.setWorkShift("N");
        employee.setEmpId(1);
        jdbcEmployeeRepository.save(employee);
    }
    @Test
    void select() throws SQLException {
        var connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamestore","root", null);
        var jdbcEmployeeRepository = new JdbcEmployeeRepository(connection);
        assertTrue(jdbcEmployeeRepository.getById(1) != null);
    }

    @Test
    void delete() throws SQLException {
        var connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamestore","root", null);
        var jdbcEmployeeRepository = new JdbcEmployeeRepository(connection);
        Employee employee = new Employee();
        employee.setEmpId(1);
        jdbcEmployeeRepository.delete(employee);
    }
    @Test
    void getAll() throws SQLException {
        var connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamestore","root", null);
        var jdbcEmployeeRepository = new JdbcEmployeeRepository(connection);
        assertTrue( jdbcEmployeeRepository.getAll() != null);
    }


}