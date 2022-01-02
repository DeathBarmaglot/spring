package com.shop.repo;

import com.shop.domain.Food;

import java.util.List;

public interface FoodRepo {
    List<Food> findByName(String name);

    Object findAll();
}
