package com.shop.service.impl;

import com.shop.dao.ProductDao;
import com.shop.dao.UserDao;
import com.shop.service.ProductService;
import com.shop.web.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceDao implements ProductService {
    private final ProductDao productDao;

    @Override
    public Product saveProduct(Product product) {
        log.info("Saving new user {} to the database", product.getName());

        return productDao.save(product);
    }
}
