package cat.uvic.teknos.m06.gamestore.domain.repositories;

import cat.uvic.teknos.m06.gamestore.domain.Exceptions.RepositoryException;
import cat.uvic.teknos.m06.gamestore.domain.models.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcCustomerRepository implements Repository<Customer,Integer > {
    private static final String INSERT = "insert into customers values (?,?,?,?)";
    private static final String UPDATE = "update customers set name = ?, set Email = ?, set where id = ?";
    private static final String SELECT_ALL = "select customer_id, name, email, postcode, EMP_ID from customers";
    private static final String SELECT = "select customer_id, name, email, postcode, EMP_ID from customers where id = ?";
    private static final String DELETE = "DELETE FROM customers WHERE customer_id = ? ";
    private final Connection connection;


    public JdbcCustomerRepository(Connection connection){this.connection =  connection;}

    @Override
    public void save(Customer customer){
        if (customer == null)
            throw new RepositoryException("The customer is null!");
        if (customer.getCustomerId() <=0)
            insert(customer);
        else
            update(customer);
    }
    private void insert(Customer customer){
        try(var prepared = connection.prepareStatement(INSERT)){
            prepared.setString(1,customer.getName());
            prepared.executeUpdate();
            var generatedKeysResultSet = prepared.getGeneratedKeys();
            if (!generatedKeysResultSet.next()) {
                throw new RepositoryException("Exception while inserting: id not generated" + customer);
            }
            customer.setCustomerId(generatedKeysResultSet.getInt(1));
        } catch (SQLException e) {
            throw new RepositoryException("Exception while inserting: " + customer, e);
        }
    }
    private void update(Customer customer){
        try {
            var prepared  = connection.prepareStatement(UPDATE);
             prepared.setInt(1, customer.getCustomerId());
            prepared.setString(2, customer.getEmail());
            prepared.setInt(3, customer.getPostcode());
            prepared.setInt(4, customer.getEmpID());
            prepared.executeUpdate();
            prepared.close();
        } catch (SQLException e) {
            throw new RepositoryException("Exception while updating: " + customer, e);
        }

    }

    @Override
    public void delete (Customer customer){
        try(var statement = connection.createStatement()){
            statement.executeUpdate(DELETE);
        } catch (SQLException e) {
            throw new RepositoryException("Exception while trying to delete", e);
        }
    }

    @Override
    public Customer getById(Integer id) {
        try(var statement = connection.createStatement()){
            var resultSet = statement.executeQuery(SELECT);
            var customer = new Customer();
            customer.setCustomerId(resultSet.getInt("customer_id"));
            customer.setName(resultSet.getString("name"));
            customer.setEmail(resultSet.getString("Email"));
            customer.setPostcode(resultSet.getInt("postcode"));
            customer.setPostcode(resultSet.getInt("EMP_ID"));
            return customer;
        } catch (SQLException e) {
            throw new RepositoryException("Exception while executing get by id", e);
        }
    }

    @Override
    public List<Customer> getAll() {
        var customers = new ArrayList<Customer>();
        try (var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                var customer = new Customer();
                customer.setCustomerId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("name"));
                customer.setEmail(resultSet.getString("Email"));
                customer.setPostcode(resultSet.getInt("postcode"));
                customer.setPostcode(resultSet.getInt("EMP_ID"));
                customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            throw new RepositoryException("Exception while executing get all", e);
        }

    }
}
