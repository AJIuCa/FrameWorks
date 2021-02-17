package ru.geekbrains.art_shop;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 128, nullable = false)
    private String manufacturer;

    @Column(length = 128,unique = true, nullable = false)
    private String title;

    @Column(length = 128, nullable = false)
    private int price;


    @ManyToOne(optional = false)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;


    @ManyToMany(mappedBy = "products" )
    private List<Client> clients;

    public Product() {
    }


    public Product(String manufacturer, String title, int price, Warehouse warehouse) {
        this.manufacturer = manufacturer;
        this.title = title;
        this.price = price;
        this.warehouse = warehouse;
    }

    public Product(String manufacturer, String title, int price) {
        this.manufacturer = manufacturer;
        this.title = title;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

//    public List<Client> getClients() {
//        return clients;
//    }
//
//    public void setClients(List<Client> clients) {
//        this.clients = clients;
//    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }


//    @Override
//    public String toString() {
//        return "\nProduct{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", manufacturer='" + manufacturer + '\'' +
//                ", price=" + price +
//                '}'+ '\'' +
//                ", warehouse=" + warehouse.getId() +
//                '}';
//    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", manufacturer='" + manufacturer + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", warehouse=" + warehouse.getId() +
                '}';
    }
}