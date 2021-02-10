package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.art_shop.ArtShopRepo;
import ru.geekbrains.art_shop.Product;

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

        ArtShopRepo artShopRepo = new ArtShopRepo(entityManagerFactory);

        artShopRepo.initRepoWithDefaultData();

        System.out.println("\n++ Find ALL products ++");
        System.out.println(artShopRepo.showAllProducts());

        System.out.println("\n++ Find Product by ID = 3 ++");
        System.out.println(artShopRepo.findProductById(3L));


        System.out.println("\n++ Update Product title with id= 3 ++");
        artShopRepo.updateProductTitle(3L,"BoneWhite");
        System.out.println(artShopRepo.findProductById(3L));

        System.out.println("\n++ Delete Product with id= 3 ++");
        artShopRepo.deleteProductById(3L);
        System.out.println(artShopRepo.showAllProducts());

        System.out.println("\n++ Insert new Product ++");
        artShopRepo.insertProduct("brushes", "kolinsky 0.5", 1);
        System.out.println(artShopRepo.showAllProducts());

    }


    public static void examples() {

        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Insert into DB---------------------------------------------------------------------------------------------

//        entityManager.getTransaction().begin();
//
//            Product product1 = new Product("paints","old rust", 35);
//            Product product2 = new Product("brushes","Kolinsky 1.0", 4);
//            Product product3 = new Product("paints","bloody red", 27);
//            Product product4 = new Product("paints","brass", 31);
//            Product product5 = new Product("pencils","HB black", 2);
//            entityManager.persist(product1);
//            entityManager.persist(product1);
//            entityManager.persist(product2);
//            entityManager.persist(product3);
//            entityManager.persist(product4);
//            entityManager.persist(product5);
//
//        entityManager.getTransaction().commit();
//        entityManager.close();


        // Select from DB----------------------------------------------------------------------------------------------

        System.out.println("\n++ SELECT FROM DB ++\n");

        System.out.println("\n++ Product with id = 1 ++\n");
        Product product = entityManager.find(Product.class, 1L);
        System.out.println(product);

        // Select from DB by JPQL lang

        System.out.println("\n++ Select ALL products ++\n");
        List<Product> productList = entityManager.createQuery("from Product", Product.class).getResultList();
        System.out.println(productList);

        System.out.println("\n++ Select products with title = BRASS ++\n");
        Object product2 = entityManager.createQuery("from Product product where product.title = :title")
                .setParameter("title", "brass")
                .getSingleResult();
        System.out.println(product2);

        // Select from DB, use SQL lang-------------------------------------------------------------------------------

        System.out.println("\n++ Select products with category = paints ++\n");

        List<Product> productList1 = entityManager.createNativeQuery("select * from products where category = 'paints'", Product.class)
                .getResultList();
        System.out.println(productList1);


        // Select from DB by Query------------------------------------------------------------------------------------

        System.out.println("\n++ Select products by query ++\n");

        List<Product> productList2 = entityManager.createNamedQuery("productsList").getResultList();
        System.out.println(productList2);

        List<Product> productList3 = entityManager.createNamedQuery("productByCategory")
                .setParameter("category", "brushes")
                .getResultList();
        System.out.println(productList3);
//        entityManager.close();

        // Update DB--------------------------------------------------------------------------------------------------

        System.out.println("\n++ Update product price ++\n");

        Product product3 = entityManager.find(Product.class, 2L);
        System.out.println("Old price = " + product3.getPrice());
        entityManager.getTransaction().begin();
        product3.setPrice(100);
        entityManager.getTransaction().commit();
        Product product4 = entityManager.find(Product.class, 2L);
        System.out.println("New price = " + product4.getPrice());
//        entityManager.close();


        // Delete from DB---------------------------------------------------------------------------------------------

        System.out.println("\n++ Delete product from DB ++\n");

        List <Product> productList6 = entityManager.createQuery("from Produce", Product.class)
                .getResultList();
        System.out.println(productList6);


        entityManager.getTransaction().begin();
        Product delProduct = entityManager.find(Product.class, 4l);
//        Object delProduct = entityManager.createQuery("from Product product where product.title = :title")
//                .setParameter("title", "bloody red")
//                .getSingleResult();
        if (delProduct != null) {
            entityManager.remove(delProduct);
            entityManager.getTransaction().commit();

            List <Product> productList5 = entityManager
                    .createQuery("from Produce", Product.class)
                    .getResultList();
            System.out.println(productList5);
        } else {
            System.out.println("Product not found");
        }

        // Delete by JPQL lang
//        entityManager.createQuery("delete from Product where price=:price")
//                .setParameter("price", 35)
//                .executeUpdate();
//        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
