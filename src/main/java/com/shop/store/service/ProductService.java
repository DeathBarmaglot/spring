package com.shop.store.service;

import com.shop.store.entity.Product;
import com.shop.store.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public Product saveProduct(Product product) {
        log.info("Saving new user {} to the database", product.getName());
        return productRepository.save(product);
    }
}
