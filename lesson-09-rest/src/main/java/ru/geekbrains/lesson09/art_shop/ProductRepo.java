package ru.geekbrains.lesson09.art_shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepo extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query("select p from Product p  where " +
       "(p.title like :productTitleFilter or :productTitleFilter is null) and " +
       "(p.price > :minPriceFilter or :minPriceFilter is null) and" +
       "(p.price < :maxPriceFilter or :maxPriceFilter is null)")

    List<Product> searchWithFilerSQL(String productTitleFilter, Integer minPriceFilter, Integer maxPriceFilter);

}