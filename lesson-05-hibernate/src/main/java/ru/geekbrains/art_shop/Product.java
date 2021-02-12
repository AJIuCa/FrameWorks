package ru.geekbrains.art_shop;

import javax.persistence.*;

@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "productByCategory", query = "from Product product where product.category=:category"),
        @NamedQuery(name ="productsList", query = "from Product")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 128, nullable = false)
    private String  category;
    @Column(length = 128,unique = true, nullable = false)
    private String title;
    @Column(length = 128, nullable = false)
    private int price;

    public Product() {
    }

    public Product(String category, String title, int price) {
        this.category = category;
        this.title = title;
        this.price = price;
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

    @Override
    public String toString() {
        return "\nProduct{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}