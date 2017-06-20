package com.shhetri.controller.rest;

import com.shhetri.exceptions.ModelNotFoundException;
import com.shhetri.model.Product;
import com.shhetri.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.AbstractMap;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<Product> all() {
        return productService.getAllProduct();
    }


    @GetMapping("/{id}")
    public Product show(@PathVariable int id) throws ModelNotFoundException {
        return productService.getProduct(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Product store(@Valid @RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product update(@Valid @RequestBody Product product, @PathVariable int id) throws ModelNotFoundException {
        Product originalProduct = productService.getProduct(id);
        originalProduct.setProductName(product.getProductName());
        originalProduct.setProductType(product.getProductType());
        originalProduct.setPrice(product.getPrice());
        originalProduct.setDescription(product.getDescription());

        return productService.save(originalProduct);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) throws ModelNotFoundException {
        Product product = productService.getProduct(id);
        productService.delete(product);

        return ResponseEntity.status(HttpStatus.GONE)
                .body(
                        new AbstractMap.SimpleEntry<>("success", String.format("Product with id %d successfully deleted.", id))
                );
    }
}
