package ru.geekbrains.lesson09.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.lesson09.art_shop.Product;
import ru.geekbrains.lesson09.art_shop.ProductRepo;
import ru.geekbrains.lesson09.art_shop.ProductSpecification;

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
    public List<ProductRepr> searchWithFilerSql(String productTitleFilter, Integer minPriceFilter, Integer maxPriceFilter) {
        return productRepo.searchWithFilerSQL(productTitleFilter, minPriceFilter, maxPriceFilter)
                .stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }


    @Override
    public Page<ProductRepr> findWithFilter(String productTitleFilter,
                                            Integer minPriceFilter,
                                            Integer maxPriceFilter,
                                            Integer pageNumber,
                                            Integer tableSize,
                                            String sort) {

        Specification<Product> spec = Specification.where(null);

        if (productTitleFilter != null && !productTitleFilter.isBlank()) {
            spec = spec.and(ProductSpecification.titleLike(productTitleFilter));
        }
        if (minPriceFilter != null) {
            spec = spec.and(ProductSpecification.minPrice(minPriceFilter));
        }
        if (maxPriceFilter != null) {
            spec = spec.and(ProductSpecification.maxPrice(maxPriceFilter));
        }
        if (sort != null) {
            return productRepo.findAll(spec, PageRequest.of(pageNumber, tableSize, Sort.by(sort).ascending()))
                    .map(ProductRepr::new);
        } else {
            return productRepo.findAll(spec, PageRequest.of(pageNumber, tableSize))
                    .map(ProductRepr::new);
        }
    }
}

