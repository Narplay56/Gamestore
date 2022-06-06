package cat.uvic.teknos.m06.gamestore.domain.repositories;

import cat.uvic.teknos.m06.gamestore.domain.models.Customer;
import cat.uvic.teknos.m06.gamestore.domain.models.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class JpaProductRepository implements Repository<Product, Integer>{
    private final EntityManagerFactory entityManagerFactory;

    public JpaProductRepository(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }
    @Override
    public void save(Product product) {
        if (product.getProductId() <= 0){
            insert(product);
        }
        else{
            update(product);
        }
    }
    private void insert(Product product){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
    }
    private void update(Product product){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(product);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Integer id) {
        var entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        var product = entityManager.find(Product.class, id);
        if (product != null) {
            entityManager.remove(product);
        }
        entityManager.getTransaction().commit();

    }

    @Override
    public Product getById(Integer id) {
        var entityManager = entityManagerFactory.createEntityManager();
        var product = entityManager.find(Product.class, id);
        entityManager.close();

        return product;
    }

    @Override
    public List<Product> getAll() {
        var entityManager = entityManagerFactory.createEntityManager();
        var query = entityManager.createQuery("SELECT product FROM Product product");
        return query.getResultList();
    }
}
