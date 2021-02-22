package ru.geekbrains.lesson09.service;


import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {

     List<ProductRepr> showAllProducts();

     Optional<ProductRepr> findProductById (long id);

     void saveProduct (ProductRepr productRepr);

     void deleteProductById (long id);

     List<ProductRepr> searchWithFilerSql(String productTitleFilter, Integer minPriceFiler, Integer maxPriceFilter);

     Page<ProductRepr> findWithFilter(String productTitleFilter,
                                      Integer minPriceFiler,
                                      Integer maxPriceFilter,
                                      Integer pageNumber,
                                      Integer tableSize,
                                      String sort);
}
