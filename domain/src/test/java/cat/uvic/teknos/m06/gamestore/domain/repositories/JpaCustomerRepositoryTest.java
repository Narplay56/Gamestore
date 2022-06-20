package cat.uvic.teknos.m06.gamestore.domain.repositories;

import cat.uvic.teknos.m06.gamestore.domain.models.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class JpaCustomerRepositoryTest {
    public static final int MODEL_TO_DELETE = 2;
    private static EntityManagerFactory entityManagerFactory;
    private static Repository <Customer, Integer> repository;

    @BeforeAll
    static void setUp() {
       entityManagerFactory = Persistence.createEntityManagerFactory("gamestart-mysql");
       repository = new JpaCustomerRepository(entityManagerFactory);

    }
    @Test
    void  save(){
        Customer customer = new Customer();
        customer.setName("Toni Liebheart");
        customer.setEmail("toni.li@uvic.cat");
        customer.setPostcode(17230);


        assertDoesNotThrow(() ->{
           repository.save(customer);
        });

        assertTrue(customer.getCustomerId() > 0);

    }
    @Test
    void  saveUpdate(){
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setName("Sara Toner");
        customer.setEmail("Sara.to@uvic.cat");
        customer.setPostcode(17520);


        assertDoesNotThrow(() ->{
           repository.save(customer);
        });

        assertTrue(customer.getCustomerId() > 0);

        var entityManager = entityManagerFactory.createEntityManager();
        var modifiedCustomer = entityManager.find(Customer.class,1 );

        assertEquals("Sara Toner", modifiedCustomer.getName());
        assertEquals("Sara.to@uvic.cat", modifiedCustomer.getEmail());
        assertEquals(17520, modifiedCustomer.getPostcode());
        entityManager.close();

    }
    @Test
    void delete(){
        var andres = repository.getById(MODEL_TO_DELETE);

        assertNotNull(andres);

        assertDoesNotThrow(() -> {
            repository.delete(MODEL_TO_DELETE);
        });

        andres = repository.getById(MODEL_TO_DELETE);

        assertNull(andres);

    }
    @Test
    void getByid(){
            var customer = repository.getById(1);

            assertNotNull(customer);
    }

    @Test
    void getAll(){
        var customers = repository.getAll();

        assertNotNull(customers);
        assertTrue(customers.size() > 0);
    }
}