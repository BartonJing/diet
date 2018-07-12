package com.barton.service;

import com.barton.domain.entity.Food;

import java.util.List;

public interface FoodService {
    int insert(Food food);

    int update(Food food);

    List<Food> selectByType(List<String> types);

}
