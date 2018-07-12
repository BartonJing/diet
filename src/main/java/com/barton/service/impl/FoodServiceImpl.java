package com.barton.service.impl;

import com.barton.dao.FoodMapper;
import com.barton.domain.entity.Food;
import com.barton.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodMapper foodMapper;
    @Override
    public int insert(Food food) {
        return foodMapper.insertSelective(food);
    }

    @Override
    public int update(Food food) {
        return foodMapper.updateByPrimaryKeySelective(food);
    }

    @Override
    public List<Food> selectByType(List<String> types) {
        return foodMapper.selectByType(types);
    }
}
