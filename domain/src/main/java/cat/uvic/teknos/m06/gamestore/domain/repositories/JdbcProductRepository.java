package cat.uvic.teknos.m06.gamestore.domain.repositories;

import cat.uvic.teknos.m06.gamestore.domain.Exceptions.RepositoryException;
import cat.uvic.teknos.m06.gamestore.domain.models.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductRepository implements Repository<Product,Integer > {
    private static final String INSERT = "insert into employees (Product_type,Product_name,Price) values (?,?,?)";
    private static final String UPDATE = "update employees set Product_type = ?, Product_name = ?, Price = ? where Product_id = ?";
    private static final String SELECT_ALL = "select * from products";
    private static final String SELECT = "select * from products where Product_id = ?";
    private static final String DELETE = "DELETE FROM products WHERE Product_id = ? ";
    private final Connection connection;


    public JdbcProductRepository(Connection connection){this.connection =  connection;}

    @Override
    public void save(Product product){
        if (product == null)
            throw new RepositoryException("The product is null!");
        if (product.getProductId() <=0)
            insert(product);
        else
            update(product);
    }
    private void insert(Product product){
        try(var prepared = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
            prepared.setString(1,product.getProductType());
            prepared.setString(2, product.getProductName());
            prepared.setFloat(3, product.getPrice());
            prepared.executeUpdate();
            var generatedKeysResultSet = prepared.getGeneratedKeys();
            if (!generatedKeysResultSet.next()) {
                throw new RepositoryException("Exception while inserting: id not generated" + product);
            }
            product.setProductId(generatedKeysResultSet.getInt(1));
        } catch (SQLException e) {
            throw new RepositoryException("Exception while inserting: " + product, e);
        }
    }
    private void update(Product product){
        try (
            var prepared  = connection.prepareStatement(UPDATE)){
            prepared.setString(1, product.getProductType());
            prepared.setString(2, product.getProductName());
            prepared.setFloat(3, product.getPrice());
            prepared.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("Exception while updating: " + product, e);
        }
    }
    @Override
    public void delete (Integer id){
        try(var preparedStatement = connection.prepareStatement(DELETE)){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("Exception while trying to delete", e);
        }
    }

    @Override
    public Product getById(Integer id) {
        try(var preparedStatement = connection.prepareStatement(SELECT)){
            Product product = null;
            preparedStatement.setInt(1,id);

            var resultSet= preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new Product();

                product.setProductId(resultSet.getInt("Product_id"));
                product.setProductType(resultSet.getString("Product_type"));
                product.setProductName(resultSet.getString("Product_name"));
                product.setPrice(resultSet.getFloat("Price"));
            }
            return product;
        } catch (SQLException e) {
            throw new RepositoryException("Exception while executing get by id", e);
        }
    }

    @Override
    public List<Product> getAll() {
        var products = new ArrayList<Product>();
        try (var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                var product = new Product();
                product.setProductId(resultSet.getInt("Product_id"));
                product.setProductType(resultSet.getString("Product_type"));
                product.setProductName(resultSet.getString("Product_name"));
                product.setPrice(resultSet.getFloat("Price"));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RepositoryException("Exception while executing get all", e);
        }

    }
}
