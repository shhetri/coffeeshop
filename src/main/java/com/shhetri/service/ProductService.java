package com.shhetri.service;

import com.shhetri.exceptions.ModelNotFoundException;
import com.shhetri.model.OrderLine;
import com.shhetri.model.Product;
import com.shhetri.model.ProductType;
import com.shhetri.repository.OrderRepository;
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
    private final OrderRepository orderRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product, int id) throws ModelNotFoundException {
        Product originalProduct = getProduct(id);
        originalProduct.setProductName(product.getProductName());
        originalProduct.setProductType(product.getProductType());
        originalProduct.setPrice(product.getPrice());
        originalProduct.setDescription(product.getDescription());

        return save(originalProduct);
    }

    public void delete(Product product) {
        List<OrderLine> orderLines = product.getOrdersLines();

        for (OrderLine orderLine : orderLines) {
            orderRepository.delete(orderLine.getOrder());
        }

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
