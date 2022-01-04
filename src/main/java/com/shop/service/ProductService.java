package com.shop.service;

import com.shop.web.entity.Product;
import com.shop.web.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    Product saveProduct(Product product);
}
