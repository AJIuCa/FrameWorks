package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.art_shop.Product;
import ru.geekbrains.art_shop.ProductRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<ProductRepr> showAllProducts() {

        return productRepo.findAll().stream().map(ProductRepr::new).collect(Collectors.toList());

    }

    @Transactional
    @Override
    public Optional<ProductRepr> findProductById(long id) {

        return productRepo.findById(id).map(ProductRepr::new);
    }

    @Transactional
    @Override
    public void saveProduct(ProductRepr productRepr) {
        productRepo.save(new Product(productRepr));
    }


    @Transactional
    @Override
    public void deleteProductById(long id) {
        productRepo.deleteById(id);
    }

    @Override
    public List<ProductRepr> searchByTitleFilter(String titleFilter) {
        return productRepo.findProductByTitleLike(titleFilter)
                .stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductRepr> searchByPriceFilter(int minPrice, int maxPrice) {
        return productRepo.findProductByPriceBetween(minPrice,maxPrice)
                .stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }
}