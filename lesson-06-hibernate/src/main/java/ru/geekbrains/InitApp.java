package ru.geekbrains;

import ru.geekbrains.art_shop.Client;
import ru.geekbrains.art_shop.Product;
import ru.geekbrains.art_shop.Warehouse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class InitApp {

    private final EntityManagerFactory entityManagerFactory;

    public InitApp(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public  void initApp () {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();



        Warehouse warehouse1 = new Warehouse("Paints", "2A");
        Warehouse warehouse2 = new Warehouse("Brushes", "1C");
        Warehouse warehouse3 = new Warehouse("Pencils", "1C");


        Product product1 = new Product("citadel","old rust", 35, warehouse1);
        Product product2 = new Product("Roubloff","Kolinsky 1.0", 4, warehouse2);
        Product product3 = new Product("vallejo","bloody red", 27,warehouse1);
        Product product4 = new Product("citadel","brass", 31,warehouse1);
        Product product5 = new Product("KOH-I-NOOR","HB black", 2,warehouse3);
        Product product6 = new Product("KOH-I-NOOR","BB black", 2, warehouse3);

        entityManager.persist(warehouse1);
        entityManager.persist(warehouse2);
        entityManager.persist(warehouse3);

        entityManager.persist(product1);
        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(product3);
        entityManager.persist(product4);
        entityManager.persist(product5);
        entityManager.persist(product6);

        List<Product> productList1 = new ArrayList<>();
        productList1.add(product1);
        productList1.add(product2);
        productList1.add(product3);
        productList1.add(product4);
        productList1.add(product5);


        List<Product> productList2 = new ArrayList<>();

        productList2.add(product2);
        productList2.add(product5);

        Client client1 = new Client("Piter", productList2);
        entityManager.persist(client1);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
