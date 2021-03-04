package ru.geekbrains.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.service.ProductRepr;
import ru.geekbrains.service.ProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/artshop")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping
//    public String startPage(Model model, @RequestParam("usernameFilter") Optional<String> usernameFilter) {
//        logger.info("List page requested");
//
//        List<ProductRepr> products;
//
//        if (usernameFilter.isPresent()) {
//            products = productService.filterSearchByTitle(usernameFilter.get());
//        } else {
//            products = productService.showAllProducts();
//        }
//        model.addAttribute("product", products);
//        return "artShop";
//    }


    @GetMapping
    public String startPage(Model model,
                            @RequestParam("minPriceFilter") Optional<Integer> minPriceFilter,
                            @RequestParam("maxPriceFilter") Optional<Integer> maxPriceFilter,
                            @RequestParam("productTitleFilter") Optional<String> titleFilter) {
        logger.info("List page requested");

        List<ProductRepr> products;

        if (minPriceFilter.isPresent() && maxPriceFilter.isPresent()) {
            products = productService.searchByPriceFilter(minPriceFilter.get(),maxPriceFilter.get());
        } else if(titleFilter.isPresent()){
            products = productService.searchByTitleFilter(titleFilter.get());
        }else {
            products = productService.showAllProducts();
        }
        model.addAttribute("product", products);
        return "artShop";
    }



    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        logger.info("Edit page for product id {} requested", id);

        model.addAttribute("product", productService.findProductById(id).orElseThrow(NotFoundException::new));
        return "product_form";
    }

    @GetMapping("/createProduct")
    public String addProduct(Model model){
        logger.info("Create new Product");

        model.addAttribute("product", new ProductRepr());
        return "product_form";
    }


    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        logger.info("Delete product with id = {}", id);

        productService.deleteProductById(id);
        return "redirect:/artshop";
    }


    @PostMapping("/update")
    public String updateProduct(@Valid ProductRepr productRepr, BindingResult result) {
        logger.info("Update endpoint requested");

        if (result.hasErrors()) {
            return "product_form";
        }
        logger.info("Product update");
        productService.saveProduct(productRepr);

        return "redirect:/artshop";
    }

}
