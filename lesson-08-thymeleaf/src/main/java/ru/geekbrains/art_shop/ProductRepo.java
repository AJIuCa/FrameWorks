package ru.geekbrains.art_shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.service.ProductRepr;

import java.util.Arrays;
import java.util.List;


@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query("select p from Product p  where " +
       "(p.title like :productTitleFilter or :productTitleFilter is null) and " +
       "(p.price > :minPriceFilter or :minPriceFilter is null) and" +
       "(p.price < :maxPriceFilter or :maxPriceFilter is null)")
    List<Product> searchWithFiler(String productTitleFilter, Integer minPriceFilter, Integer maxPriceFilter);


//    @Query("select p from Product p  where " +
//            "(p.title like :productTitleFilter or :productTitleFilter is null) and " +
//            "(p.price > :minPriceFilter or :minPriceFilter is null) and" +
//            "(p.price < :maxPriceFilter or :maxPriceFilter is null)")
//    List<Product> searchWithFiler(@Param("productTitleFilter") String productTitleFilter,
//                                  @Param ("minPriceFilter") Integer minPriceFilter,
//                                  @Param ("maxPriceFilter") Integer maxPriceFilter);

}