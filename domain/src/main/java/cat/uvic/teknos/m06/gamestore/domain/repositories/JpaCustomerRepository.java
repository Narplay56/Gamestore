package cat.uvic.teknos.m06.gamestore.domain.repositories;

import cat.uvic.teknos.m06.gamestore.domain.models.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class JpaCustomerRepository implements Repository<Customer, Integer>{
    private final EntityManagerFactory entityManagerFactory;

    public JpaCustomerRepository(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }
    @Override
    public void save(Customer customer) {
        if (customer.getCustomerId() <= 0){
            insert(customer);
        }
        else{
            update(customer);
        }
    }
    private void insert(Customer customer){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
    }
    private void update(Customer customer){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(customer);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Integer id) {
        var entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        var customer = entityManager.find(Customer.class, id);
        if (customer != null) {
            entityManager.remove(customer);
        }
        entityManager.getTransaction().commit();

    }

    @Override
    public Customer getById(Integer id) {
        var entityManager = entityManagerFactory.createEntityManager();
        var customer = entityManager.find(Customer.class, id);
        entityManager.close();

        return customer;
    }

    @Override
    public List<Customer> getAll() {
        var entityManager = entityManagerFactory.createEntityManager();
        var query = entityManager.createQuery("SELECT customer FROM Customer customer");
        return query.getResultList();
    }
}
