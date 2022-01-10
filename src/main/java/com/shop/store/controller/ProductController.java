package com.shop.store.controller;

import com.shop.store.entity.Product;
import com.shop.store.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
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
        return update(model);
    }

    @RequestMapping(path = "/products/add", method = RequestMethod.GET)
    public String add() {
        return "add";
    }

    @RequestMapping(path = "/products/remove/{id}", method = RequestMethod.POST)
    public String deleteProduct(@RequestParam Long id, Map<String, Object> model) {
        productRepository.deleteById(id);
        return update(model);
    }

    @RequestMapping(path = "/products/add", method = RequestMethod.POST)
    public String addProducts(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam int price,
            Map<String, Object> model) {
        Product product = new Product(name, description, price, LocalDateTime.now());
        productRepository.save(product);
        return update(model);
    }

    @RequestMapping(path = "/products/edit/{id}", method = RequestMethod.POST)
    public String editProducts(
            @PathVariable long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam int price,
            Map<String, Object> model) {
        Product existed = productRepository.findById(id).get();
        Assert.notNull(existed, "product not found");
        existed.setPrice(price);
        existed.setName(name);
        existed.setDescription(description);
        existed.setDate(LocalDateTime.now());
        productRepository.save(existed);
        return update(model);
    }

    private String update(Map<String, Object> model) {
        Iterable<Product> products = productRepository.findAll();
        model.put("products", products);
        return "products";
    }
    //TODO find filter by name, price
}