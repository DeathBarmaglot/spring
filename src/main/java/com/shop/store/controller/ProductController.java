package com.shop.store.controller;

import com.shop.store.entity.Product;
import com.shop.store.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String login() {
        return "redirect:/products";
    }

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public String products(Map<String, Object> model) {
        Iterable<Product> products = productRepository.findAll();

        model.put("products", products);
        return "products";
    }

    @RequestMapping(path = "/products", method = RequestMethod.POST)
    public String deleteProduct(@RequestParam Long id, Map<String, Object> model) {
        productRepository.deleteById(id);
        model.put("products", productRepository.findAll());
        return "products";
    }


    @RequestMapping(path = "/products/add", method = RequestMethod.GET)
    public String add() {
        return "add";
    }

    @RequestMapping(path = "/products/add", method = RequestMethod.POST)
    public String addProducts(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam int price,
            Map<String, Object> model) {
        Product product = new Product(name, description, price, LocalDateTime.now());
        productRepository.save(product);
        Iterable<Product> products = productRepository.findAll();
        model.put("products", products);
        return "products";
    }

    @RequestMapping(path = "/products/edit", method = RequestMethod.POST)
    public String editProducts(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam int price,
            Map<String, Object> model) {
        productRepository.deleteById(id);
        Product product = new Product(name, description, price, LocalDateTime.now());
        productRepository.save(product);
        Iterable<Product> products = productRepository.findAll();
        model.put("products", products);
        return "products";
    }
    //TODO find filter
}