package cat.uvic.teknos.m06.gamestore.domain.repositories;

import cat.uvic.teknos.m06.gamestore.domain.Exceptions.RepositoryException;
import cat.uvic.teknos.m06.gamestore.domain.models.Employee;
import cat.uvic.teknos.m06.gamestore.domain.models.Shipment;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcShipmentRepository implements Repository<Shipment,Integer > {
    private static final String INSERT = "insert into Shipments (company,Order_id) values (?,?)";
    private static final String UPDATE = "update Shipments set company = ?, Order_id = ? where Shipment_id = ?";
    private static final String SELECT_ALL = "select * from Shipments";
    private static final String SELECT = "select * from Shipments where Shipment_id = ?";
    private static final String DELETE = "DELETE FROM Shipments WHERE Shipment_id = ? ";
    private final Connection connection;


    public JdbcShipmentRepository(Connection connection){this.connection =  connection;}

    @Override
    public void save(Shipment shipment){
        if (shipment == null)
            throw new RepositoryException("The employee is null!");
        if (shipment.getShipmentID() <=0)
            insert(shipment);
        else
            update(shipment);
    }
    private void insert(Shipment shipment){
        try(var prepared = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
            prepared.setString(1,shipment.getCompany());
            prepared.setInt(2, shipment.getOrderId());
            prepared.executeUpdate();
            var generatedKeysResultSet = prepared.getGeneratedKeys();
            if (!generatedKeysResultSet.next()) {
                throw new RepositoryException("Exception while inserting: id not generated" + shipment);
            }
            shipment.setShipmentID(generatedKeysResultSet.getInt(1));
        } catch (SQLException e) {
            throw new RepositoryException("Exception while inserting: " + shipment, e);
        }
    }
    private void update(Shipment shipment){
        try (
            var prepared  = connection.prepareStatement(UPDATE)){
            prepared.setString(1, shipment.getCompany());
            prepared.setInt(2, shipment.getOrderId());
            prepared.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("Exception while updating: " + shipment, e);
        }
    }
    @Override
    public void delete (Shipment shipment){
        try(var preparedStatement = connection.prepareStatement(DELETE)){
            preparedStatement.setInt(1,shipment.getShipmentID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("Exception while trying to delete", e);
        }
    }

    @Override
    public Shipment getById(Integer id) {
        try(var preparedStatement = connection.prepareStatement(SELECT)){
            Shipment shipment = null;
            preparedStatement.setInt(1,id);

            var resultSet= preparedStatement.executeQuery();
            if (resultSet.next()) {
                shipment = new Shipment();

                shipment.setShipmentID(resultSet.getInt("Shipment_id"));
                shipment.setCompany(resultSet.getString("company"));
                shipment.setOrderId(resultSet.getInt("Order_id"));
            }
            return shipment;
        } catch (SQLException e) {
            throw new RepositoryException("Exception while executing get by id", e);
        }
    }

    @Override
    public List<Shipment> getAll() {
        var shipments = new ArrayList<Shipment>();
        try (var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                var shipment = new Shipment();
                shipment.setShipmentID(resultSet.getInt("Shipment_id"));
                shipment.setCompany(resultSet.getString("company"));
                shipment.setOrderId(resultSet.getInt("Order_id"));
                shipments.add(shipment);
            }
            return shipments;
        } catch (SQLException e) {
            throw new RepositoryException("Exception while executing get all", e);
        }

    }
}
