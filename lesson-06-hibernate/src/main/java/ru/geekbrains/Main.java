package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.art_shop.Client;
import ru.geekbrains.art_shop.Product;
import ru.geekbrains.art_shop.Warehouse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        startHomeWorkApp();
    }

    public static void startHomeWorkApp() {

        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory();

        // Create DB and Tables----------------------------------------------------------------------------------------
        new InitApp(entityManagerFactory).initApp();

        // -----------------------------------TEST OUR DB-------------------------------------------------------------
//        EntityManager  entityManager = entityManagerFactory.createEntityManager();

        // Select from DB ---------------------------------------------------------------------------------------------
//        System.out.println("Select products with category = paints");
//        Warehouse warehouse = entityManager.find(Warehouse.class, 1L);
//        List <Product> products = warehouse.getProducts();
//        for (Product product: products) {
//            System.out.println(product);
//        }
//
//        System.out.println("\nUser bought: ");
//        Client client = entityManager.find(Client.class, 1L);
//        List <Product> products1 = client.getProducts();
//        for (Product product: products1) {
//            System.out.println(product);
//        }
//        entityManager.close();

    }

}
