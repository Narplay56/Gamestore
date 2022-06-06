package cat.uvic.teknos.m06.gamestore.domain.repositories;

import cat.uvic.teknos.m06.gamestore.domain.models.Customer;
import cat.uvic.teknos.m06.gamestore.domain.models.Shipment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class JpaShipmentRepository implements Repository<Shipment, Integer>{
    private final EntityManagerFactory entityManagerFactory;

    public JpaShipmentRepository(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }
    @Override
    public void save(Shipment shipment) {
        if (shipment.getShipmentID() <= 0){
            insert(shipment);
        }
        else{
            update(shipment);
        }
    }
    private void insert(Shipment shipment){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(shipment);
        entityManager.getTransaction().commit();
    }
    private void update(Shipment shipment){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(shipment);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Integer id) {
        var entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        var shipment = entityManager.find(Shipment.class, id);
        if (shipment != null) {
            entityManager.remove(shipment);
        }
        entityManager.getTransaction().commit();

    }
    @Override
    public Shipment getById(Integer id) {
        var entityManager = entityManagerFactory.createEntityManager();
        var shipment = entityManager.find(Shipment.class, id);
        entityManager.close();

        return shipment;
    }

    @Override
    public List<Shipment> getAll() {
        var entityManager = entityManagerFactory.createEntityManager();
        var query = entityManager.createQuery("SELECT shipment FROM Shipment shipment");
        return query.getResultList();
    }
}
