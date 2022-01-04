package com.shop.web.controller;

import com.shop.dao.ProductDao;
import com.shop.web.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ProductController {
    @Autowired
    private ProductDao productDao;

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public String products(Map<String, Object> model) {
        model.put("products", productDao.findAll());
        return "products";
    }

    @RequestMapping(path = "/products", method = RequestMethod.POST)
    public String deleteProduct(@RequestParam Long id, Map<String, Object> model) {
        productDao.deleteById(id);
        return "redirect:/products";
    }

    @RequestMapping(path = "/products/add", method = RequestMethod.GET)
    public String add(Map<String, Object> model) {
        return "add";
    }


    @RequestMapping(path = "/products/add", method = RequestMethod.POST)
    public String addProducts(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam int price,
            Map<String, Object> model) {
        Product product = new Product(name, description, price);
        productDao.save(product);
        Iterable<Product> products = productDao.findAll();
        model.put("products", products);
        return "products";
    }

    @RequestMapping(path = "/filter", method = RequestMethod.POST)
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Product> products;

        if (filter != null && filter.isEmpty()) {
            products = productDao.findByName(filter);
        } else {
            products = productDao.findAll();
        }
        model.put("products", products);
        return "products";
    }
}