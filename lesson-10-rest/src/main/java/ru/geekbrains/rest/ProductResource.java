package ru.geekbrains.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.controller.BadRequestException;
import ru.geekbrains.controller.NotFoundException;
import ru.geekbrains.service.ProductRest;
import ru.geekbrains.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductResource {

    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService){
        this.productService = productService;
    }

    @GetMapping(path = "/all" +
            "", produces = "application/json")
    public List<ProductRest> showAllRestProducts() {
        return productService.showAllRestProducts();
    }

    @GetMapping(path = "/{id}")
    public ProductRest findByRestID(@PathVariable("id") Long id){
        return productService.findRestProductById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping(consumes = "application/json")
    public ProductRest createNewProduct(@RequestBody ProductRest productRest){
        if (productRest.getId() != null ){
            throw new BadRequestException();
        } else {
            productService.saveRestProduct(productRest);
            return productRest;
        }
    }


    @PutMapping(consumes = "application/json")
    public void update(@RequestBody ProductRest productRest){
        if (productRest.getId() == null){
            throw new BadRequestException();
        }
        productService.saveRestProduct(productRest);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteByRestId(@PathVariable("id") Long id){
        productService.deleteRestProductById(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundException(NotFoundException ex) {
        return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> badRequestException(BadRequestException ex) {
        return new ResponseEntity<>("Bad Request", HttpStatus.NOT_FOUND);
    }
}
