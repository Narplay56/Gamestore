package cat.uvic.teknos.m06.gamestore.domain.repositories;

import cat.uvic.teknos.m06.gamestore.domain.models.Shipment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class JpaShipmentRepositoryTest {
    public static final int MODEL_TO_DELETE = 2;
    private static EntityManagerFactory entityManagerFactory;
    private static Repository <Shipment, Integer> repository;

    @BeforeAll
    static void setUp() {
       entityManagerFactory = Persistence.createEntityManagerFactory("gamestart-mysql");
       repository = new JpaShipmentRepository(entityManagerFactory);

    }
    @Test
    void  save(){
        Shipment shipment = new Shipment();
        shipment.setCompany("SEUR");

        assertDoesNotThrow(() ->{
           repository.save(shipment);
        });

        assertTrue(shipment.getShipmentID() > 0);

    }
    @Test
    void  saveUpdate(){
        Shipment shipment = new Shipment();
        shipment.setShipmentID(1);
        shipment.setCompany("Express");

        assertDoesNotThrow(() ->{
           repository.save(shipment);
        });

        assertTrue(shipment.getShipmentID() > 0);

        var entityManager = entityManagerFactory.createEntityManager();
        var modifiedShipment = entityManager.find(Shipment.class,1 );

        assertEquals("Express", modifiedShipment.getCompany());

        entityManager.close();

    }
    @Test
    void delete(){
        var correos = repository.getById(MODEL_TO_DELETE);

        assertNotNull(correos);

        assertDoesNotThrow(() -> {
            repository.delete(MODEL_TO_DELETE);
        });

        correos = repository.getById(MODEL_TO_DELETE);

        assertNull(correos);

    }
    @Test
    void getByid(){
            var shipment = repository.getById(1);

            assertNotNull(shipment);
    }

    @Test
    void getAll(){
        var shipments = repository.getAll();

        assertNotNull(shipments);
        assertTrue(shipments.size() > 0);
    }
}