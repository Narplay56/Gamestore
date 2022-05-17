package cat.uvic.teknos.m06.gamestore.domain.repositories;

import cat.uvic.teknos.m06.gamestore.domain.Exceptions.RepositoryException;
import cat.uvic.teknos.m06.gamestore.domain.models.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class CustomerRepository {
    private static final String INSERT = "insert into customer values (?,?,?,?)";
    private static final String UPDATE = "update customer {name} set values (?)";
    private final Connection connection;

    public CustomerRepository(Connection connection){this.connection =  connection;}

    public void save(Customer customer){
        if (customer == null)
            throw new RepositoryException("The customer is null!");
        if (customer.getCustomerId() <=0)
            insert(customer);
        else
            update(customer);
    }
    private void insert(Customer customer){
        try {
            var prepared  = connection.prepareStatement(INSERT);
            prepared.setInt(1, customer.getCustomerId());
            prepared.setString(2, customer.getEmail());
            prepared.setInt(3, customer.getPostcode());
            prepared.setInt(4, customer.getEmpID());
            prepared.executeUpdate();
            prepared.close();
        } catch (SQLException e) {
            throw new RepositoryException(e);
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
            throw new RepositoryException(e);
        }

    }
    public void delete (Customer customer){

    }
    public List<Customer> getAll(){
        return null;
    }

}
