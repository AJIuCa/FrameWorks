package ru.geekbrains.art_shop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class WarehouseRepo {

    private final EntityManagerFactory entityManagerFactory;

    public WarehouseRepo(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void initRepoWithDefaultData() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Warehouse warehouse1 = new Warehouse("Paints", "2A");
        Warehouse warehouse2 = new Warehouse("Brushes", "1C");
        Warehouse warehouse3 = new Warehouse("Pencils", "1C");

        entityManager.persist(warehouse1);
        entityManager.persist(warehouse2);
        entityManager.persist(warehouse3);

        entityManager.getTransaction().commit();
        entityManager.close();

    }


    public List<Warehouse> showAllPosition() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Warehouse> positionList = entityManager.createQuery("from Warehouse", Warehouse.class).getResultList();
        entityManager.close();
        return positionList;
    }


    public Warehouse findPositionById (long id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Warehouse warehouse = entityManager.find(Warehouse.class, id);
        entityManager.close();
        return warehouse;

    }


    public void insertCategory (String category, String warehouseZone) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Warehouse warehouse = new Warehouse(category, warehouseZone);
        entityManager.persist(warehouse);

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void insertCategory (Warehouse warehouse) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(warehouse);

        entityManager.getTransaction().commit();
        entityManager.close();

    }


    public void updateCategoryName (long id, String name) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Warehouse warehouse = entityManager.find(Warehouse.class, id);
        entityManager.getTransaction().begin();
        warehouse.setCategory(name);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void deletePositionById (long id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Object delPosition = entityManager.find(Warehouse.class, id);
        if (delPosition != null) {
            entityManager.remove(delPosition);
            entityManager.getTransaction().commit();
        } else {
            System.out.println("Position not found");
        }
        entityManager.close();
    }

}
