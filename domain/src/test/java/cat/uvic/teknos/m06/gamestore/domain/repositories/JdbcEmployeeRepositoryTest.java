package cat.uvic.teknos.m06.gamestore.domain.repositories;

import cat.uvic.teknos.m06.gamestore.domain.models.Employee;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class JdbcEmployeeRepositoryTest {

    @Test
    void save() throws SQLException {
        var connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root", null);
        var con = new JdbcEmployeeRepository(connection);

    }

}