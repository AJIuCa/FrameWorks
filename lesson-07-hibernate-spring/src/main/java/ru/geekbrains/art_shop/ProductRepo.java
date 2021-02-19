package ru.geekbrains.art_shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findProductByTitleLike(String title);
    List<Product> findProductByPriceBetween(int minPriceFilter, int maxPriceFilter);

}