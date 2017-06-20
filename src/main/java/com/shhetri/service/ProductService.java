package com.shhetri.service;

import com.shhetri.exceptions.ModelNotFoundException;
import com.shhetri.model.Product;
import com.shhetri.model.ProductType;
import com.shhetri.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    public Product getProduct(int productId) throws ModelNotFoundException {
        Optional<Product> product = Optional.ofNullable(productRepository.findOne(productId));

        return product.orElseThrow(() -> new ModelNotFoundException(Product.class.getSimpleName(), productId));
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public List<Product> findByTextSearch(String criteria) {
        if (!criteria.contains("%")) {
            criteria = "%" + criteria + "%";
        }
        return productRepository.findByProductNameLikeOrDescriptionLikeAllIgnoreCase(criteria, criteria);
    }

    public List<Product> findByPrice(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> findByProductType(ProductType productType) {
        return productRepository.findByProductType(productType);
    }
}
