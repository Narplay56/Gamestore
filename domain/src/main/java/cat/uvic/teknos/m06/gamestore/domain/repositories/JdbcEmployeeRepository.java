package cat.uvic.teknos.m06.gamestore.domain.repositories;

import cat.uvic.teknos.m06.gamestore.domain.Exceptions.RepositoryException;
import cat.uvic.teknos.m06.gamestore.domain.models.Customer;
import cat.uvic.teknos.m06.gamestore.domain.models.Employee;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcEmployeeRepository implements Repository<Employee,Integer > {
    private static final String INSERT = "insert into employees (Full_name,Work_shift) values (?,?)";
    private static final String UPDATE = "update employees set Full_name = ?, set Work_shift = ?";
    private static final String SELECT_ALL = "select * from customers";
    private static final String SELECT = "select customer_id, name, email, postcode, EMP_ID from customers where id = ?";
    private static final String DELETE = "DELETE FROM employees WHERE emp_id = ? ";
    private final Connection connection;


    public JdbcEmployeeRepository(Connection connection){this.connection =  connection;}

    @Override
    public void save(Employee employee){
        if (employee == null)
            throw new RepositoryException("The employee is null!");
        if (employee.getEmpId() <=0)
            insert(employee);
        else
            update(employee);
    }
    private void insert(Employee employee){
        try(var prepared = connection.prepareStatement(INSERT)){
            prepared.setString(2,employee.getName());
            prepared.setString(3, employee.getWorkShift());
            prepared.executeUpdate();
            var generatedKeysResultSet = prepared.getGeneratedKeys();
            if (!generatedKeysResultSet.next()) {
                throw new RepositoryException("Exception while inserting: id not generated" + employee);
            }
            employee.setEmpId(generatedKeysResultSet.getInt(1));
        } catch (SQLException e) {
            throw new RepositoryException("Exception while inserting: " + employee, e);
        }
    }
    private void update(Employee employee){
        try (
            var prepared  = connection.prepareStatement(UPDATE)){
             prepared.setInt(1, employee.getEmpId());
            prepared.setString(2, employee.getName());
            prepared.setString(3, employee.getWorkShift());
            prepared.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("Exception while updating: " + employee, e);
        }

    }

    @Override
    public void delete (Employee customer){
        try(var preparedStatement = connection.prepareStatement(DELETE)){
            preparedStatement.setInt(1,customer.getEmpId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("Exception while trying to delete", e);
        }
    }

    @Override
    public Employee getById(Integer id) {
        try(var preparedStatement = connection.prepareStatement(SELECT)){
            preparedStatement.setInt(1,id);
            var resultSet= preparedStatement.executeQuery();
            var employee = new Employee();
            if (resultSet.next()) {
                employee.setEmpId(resultSet.getInt("emp_id"));
                employee.setName(resultSet.getString("full_name"));
            }
            return employee;
        } catch (SQLException e) {
            throw new RepositoryException("Exception while executing get by id", e);
        }
    }

    @Override
    public List<Employee> getAll() {
        var employees = new ArrayList<Employee>();
        try (var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                var employee = new Employee();
                employee.setEmpId(resultSet.getInt("emp_id"));
                employee.setName(resultSet.getString("full_name"));
                employees.add(employee);
            }
            return employees;
        } catch (SQLException e) {
            throw new RepositoryException("Exception while executing get all", e);
        }

    }
}
