package cat.uvic.teknos.m06.gamestore.domain.repositories;

import cat.uvic.teknos.m06.gamestore.domain.models.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class JpaProductRepositoryTest {
    public static final int MODEL_TO_DELETE = 2;
    private static EntityManagerFactory entityManagerFactory;
    private static Repository <Product, Integer> repository;

    @BeforeAll
    static void setUp() {
       entityManagerFactory = Persistence.createEntityManagerFactory("gamestart-mysql");
       repository = new JpaProductRepository(entityManagerFactory);

    }
    @Test
    void  save(){
        Product product = new Product();
        product.setProductType("videogame");
        product.setProductName("Zelda");
        product.setPrice(35.99f);

        assertDoesNotThrow(() ->{
           repository.save(product);
        });

        assertTrue(product.getProductId() > 0);

    }
    @Test
    void  saveUpdate(){
        Product product = new Product();
        product.setProductId(1);
        product.setProductType("Headset");
        product.setProductName("Super Mario Bros headset");
        product.setPrice(62f);

        assertDoesNotThrow(() ->{
           repository.save(product);
        });

        assertTrue(product.getProductId() > 0);

        var entityManager = entityManagerFactory.createEntityManager();
        var modifiedProduct = entityManager.find(Product.class,1 );

        assertEquals("Headset", modifiedProduct.getProductType());
        assertEquals("Super Mario Bros headset", modifiedProduct.getProductName());
        assertEquals(62f, modifiedProduct.getPrice());

        entityManager.close();

    }
    @Test
    void delete(){
        var PS5 = repository.getById(MODEL_TO_DELETE);

        assertNotNull(PS5);

        assertDoesNotThrow(() -> {
            repository.delete(MODEL_TO_DELETE);
        });

        PS5 = repository.getById(MODEL_TO_DELETE);

        assertNull(PS5);

    }
    @Test
    void getByid(){
            var product = repository.getById(1);

            assertNotNull(product);
    }

    @Test
    void getAll(){
        var products = repository.getAll();

        assertNotNull(products);
        assertTrue(products.size() > 0);
    }
}