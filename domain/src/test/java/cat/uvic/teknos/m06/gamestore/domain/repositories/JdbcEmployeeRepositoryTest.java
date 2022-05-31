package cat.uvic.teknos.m06.gamestore.domain.repositories;

import cat.uvic.teknos.m06.gamestore.domain.models.Employee;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class JdbcEmployeeRepositoryTest {
    @Test
<<<<<<< HEAD
    void save() throws SQLException {
        var connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamestore", "root", null);
=======
    void saveInsert() throws SQLException {
        var connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamestore","root", null);
>>>>>>> a3ed666d7b385815c568662ed213b04c8951de7d
        var jdbcEmployeeRepository = new JdbcEmployeeRepository(connection);
        Employee employee = new Employee();
        employee.setName("Matias Lazo");
        employee.setWorkShift("M");
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
<<<<<<< HEAD
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
=======
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


>>>>>>> a3ed666d7b385815c568662ed213b04c8951de7d
}