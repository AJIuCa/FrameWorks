package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.geekbrains.art_shop.BasketProduct;
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


    @GetMapping
    public String startPage(Model model,
                            @RequestParam("productTitleFilter") Optional<String> productTitleFilter,
                            @RequestParam("minPriceFilter") Optional<Integer> minPriceFilter,
                            @RequestParam("maxPriceFilter") Optional<Integer> maxPriceFilter,
                            @RequestParam("pageNumber") Optional<Integer> pageNumber,
                            @RequestParam("tableSize") Optional<Integer> tableSize,
                            @RequestParam("sort") Optional<String> sortBy) {
        logger.info("List page requested");


        Page<ProductRepr> products = productService.findWithFilter(
                productTitleFilter.filter(s -> !s.isBlank()).orElse(null),
                minPriceFilter.orElse(null),
                maxPriceFilter.orElse(null),
                pageNumber.orElse(1) - 1,
                tableSize.orElse(5),
                sortBy.orElse(null)
        );
        model.addAttribute("products", products);
        return "artShop";
    }


    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        logger.info("Edit page for products id {} requested", id);

        model.addAttribute("product", productService.findProductById(id).orElseThrow(NotFoundException::new));
        return "product_form";
    }

    @GetMapping("/createProduct")
    public String addProduct(Model model) {
        logger.info("Create new Product");

        model.addAttribute("product", new ProductRepr());
        return "product_form";
    }


    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        logger.info("Delete products with id = {}", id);

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


    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("product_not_found");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }


    @GetMapping("/basket")
    public String getBasket(Model model) {
        List<BasketProduct> basketProducts;
        basketProducts = productService.showBasket();
        model.addAttribute("basketProducts",basketProducts);

        return "basket";

    }

    @GetMapping("/basket/add/{id}")
    public String addProductToBasket(@PathVariable("id") Long id, Model model) {

        model.addAttribute("basketProducts", productService.addProductToBasketById(id));

        return "redirect:/artshop";

    }

    @DeleteMapping("/basket/remove/{id}")
    public String deleteBasketProduct(@PathVariable("id") Long id) {

        productService.deleteBasketProductById(id);
        return "redirect:/artshop/basket";
    }

}
