package cat.uvic.teknos.m06.gamestore.domain.repositories;

import cat.uvic.teknos.m06.gamestore.domain.Exceptions.RepositoryException;
import cat.uvic.teknos.m06.gamestore.domain.models.Customer;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcCustomerRepository implements Repository<Customer,Integer > {
    private static final String INSERT = "insert into customers (Name,Email,Postcode,EMP_ID) values (?,?,?,?)";
    private static final String UPDATE = "update customers set Name = ?, Email = ?, Postcode = ?, Emp_id= ? where Customer_id = ?";
    private static final String SELECT_ALL = "select * from customers";
    private static final String SELECT = "select * from customers where Customer_id = ?";
    private static final String DELETE = "DELETE FROM customers WHERE Customer_id = ? ";
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
        try(var prepared = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
            prepared.setString(1,customer.getName());
            prepared.setString(2,customer.getEmail());
            prepared.setInt(3,customer.getPostcode());
            prepared.setInt(4,customer.getEmpID());
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
            prepared.setString(1, customer.getName());
            prepared.setString(2, customer.getEmail());
            prepared.setInt(3, customer.getPostcode());
            prepared.setInt(4, customer.getEmpID());
            prepared.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("Exception while updating: " + customer, e);
        }

    }

    public void delete (Integer id){
        try(var preparedStatement = connection.prepareStatement(DELETE)){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("Exception while trying to delete", e);
        }
    }

    @Override
    public Customer getById(Integer id) {
        try(var preparedStatement = connection.prepareStatement(SELECT)){
            Customer customer = null;
            preparedStatement.setInt(1,id);

            var resultSet= preparedStatement.executeQuery();
            if (resultSet.next()) {
                customer = new Customer();

                customer.setCustomerId(resultSet.getInt("Customer_id"));
                customer.setName(resultSet.getString("Name"));
                customer.setEmail(resultSet.getString("Email"));
                customer.setPostcode(resultSet.getInt("Postcode"));
                customer.setEmpID(resultSet.getInt("EMP_ID"));
            }
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
