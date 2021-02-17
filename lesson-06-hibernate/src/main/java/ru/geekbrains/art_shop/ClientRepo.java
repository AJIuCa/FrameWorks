package ru.geekbrains.art_shop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ClientRepo {


    private final EntityManagerFactory entityManagerFactory;

    public ClientRepo(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void initRepoWithDefaultData() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Client client1 = new Client("Boris");
        Client client2 = new Client("Magnus");
        Client client3 = new Client("Vera");

        entityManager.persist(client1);
        entityManager.persist(client2);
        entityManager.persist(client3);

        entityManager.getTransaction().commit();
        entityManager.close();

    }


    public List<Client> showAllClients() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Client> clientList = entityManager.createQuery("from Client", Client.class).getResultList();
        entityManager.close();
        return clientList;
    }


    public Client findClientById (long id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Client client = entityManager.find(Client.class, id);
        entityManager.close();
        return client;

    }


    public void insertClient (String clientName) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Client client = new Client(clientName);
            entityManager.persist(client);

            entityManager.getTransaction().commit();

        } catch (Exception e) {

            entityManager.getTransaction().rollback();

        }finally {
            if ( entityManager != null) {
                entityManager.close();
            }
        }
    }

    public void insertClient (Client client) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(client);

        entityManager.getTransaction().commit();
        entityManager.close();

    }


    public void updateClientName (long id, String name) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Client client = entityManager.find(Client.class, id);
        entityManager.getTransaction().begin();
        client.setClientName(name);
        entityManager.getTransaction().commit();
        entityManager.close();

    }


    public void deleteClientById (long id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Object delClient = entityManager.find(Client.class, id);
        if (delClient != null) {
            entityManager.remove(delClient);
            entityManager.getTransaction().commit();
        } else {
            System.out.println("Client not found");
        }
        entityManager.close();
    }

}
