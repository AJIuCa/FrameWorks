package ru.geekbrains.art_shop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ArtShopRepo {

    private final EntityManagerFactory entityManagerFactory;

    public ArtShopRepo(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void initRepoWithDefaultData() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product product1 = new Product("citadel","old rust", 35);
        Product product2 = new Product("Roubloff","Kolinsky 1.0", 4);
        Product product3 = new Product("vallejo","bloody red", 27);
        Product product4 = new Product("citadel","brass", 31);
        Product product5 = new Product("KOH-I-NOOR","HB black", 2);
        Product product6 = new Product("KOH-I-NOOR","BB black", 2);
        entityManager.persist(product1);
        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(product3);
        entityManager.persist(product4);
        entityManager.persist(product5);
        entityManager.persist(product6);

        entityManager.getTransaction().commit();
        entityManager.close();

    }


    public List<Product> showAllProducts() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Product> productList = entityManager.createQuery("from Product", Product.class).getResultList();
        entityManager.close();
        return productList;
    }


    public Product findProductById (long id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Product product = entityManager.find(Product.class, id);
        entityManager.close();
        return product;

    }


    public void insertProduct (String category, String title, int price) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product product = new Product(category,title, price);
        entityManager.persist(product);

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void insertProduct (Product product) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(product);

        entityManager.getTransaction().commit();
        entityManager.close();

    }


    public void updateProductPrice (long id, int price) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Product product = entityManager.find(Product.class, id);
        entityManager.getTransaction().begin();
        product.setPrice(price);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void updateProductTitle (long id, String title) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Product product = entityManager.find(Product.class, id);
        entityManager.getTransaction().begin();
        product.setTitle(title);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void updateProductCategory (long id, String category) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Product product = entityManager.find(Product.class, id);
        entityManager.getTransaction().begin();
        product.setTitle(category);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void deleteProductById (long id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Object delProduct = entityManager.find(Product.class, id);
        if (delProduct != null) {
            entityManager.remove(delProduct);
            entityManager.getTransaction().commit();
        } else {
            System.out.println("Product not found");
        }
        entityManager.close();
    }

}