package ru.geekbrains.service;


import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {

     List<ProductRepr> showAllProducts();
     List<ProductRest> showAllRestProducts();

     Optional<ProductRepr> findProductById (Long id);
     Optional<ProductRest> findRestProductById (Long id);

     void saveProduct (ProductRepr productRepr);
     void saveRestProduct (ProductRest productRest);

     void deleteProductById (Long id);
     void deleteRestProductById (Long id);


     Page<ProductRepr> findWithFilter(String productTitleFilter,
                                      Integer minPriceFiler,
                                      Integer maxPriceFilter,
                                      Integer pageNumber,
                                      Integer tableSize,
                                      String sort);
}
