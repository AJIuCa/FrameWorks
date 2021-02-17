package ru.geekbrains.art_shop;

import javax.persistence.*;
import java.util.List;



@Entity
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 128, nullable = false)
    private String category;

    @Column(length = 128, nullable = false)
    private String warehouseZone;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<Product> products;


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Warehouse() {
    }

    public Warehouse(String category, String warehouseZone) {
        this.category = category;
        this.warehouseZone = warehouseZone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWarehouseZone() {
        return warehouseZone;
    }

    public void setWarehouseZone(String warehouseZone) {
        this.warehouseZone = warehouseZone;
    }


    @Override
    public String toString() {
        return "\nWarehouse{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", warehouseZone='" + warehouseZone + '\'' +
                '}';
    }
}
