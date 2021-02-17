package ru.geekbrains.service;


import java.util.List;
import java.util.Optional;

public interface ProductService {

     List<ProductRepr> showAllProducts();

     Optional<ProductRepr> findProductById (long id);

     void saveProduct (ProductRepr productRepr);

     void deleteProductById (long id);

     List<ProductRepr> searchByTitleFilter(String titleFilter);

     List<ProductRepr> searchByPriceFilter (int minPrice, int maxPrice);

}
