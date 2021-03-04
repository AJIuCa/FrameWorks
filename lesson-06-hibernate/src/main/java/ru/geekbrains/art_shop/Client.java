package ru.geekbrains.art_shop;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 128, nullable = false)
    private String  clientName;

    @ManyToMany
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Client() {
    }

    public Client(String clientName, List<Product> products) {
        this.clientName = clientName;
        this.products = products;
    }

    public Client(String clientName) {
        this.clientName = clientName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public String toString() {
        return "\nClient{" +
                "id=" + id + '\'' +
                ", clientName='" + clientName +
                '}';
    }
}
