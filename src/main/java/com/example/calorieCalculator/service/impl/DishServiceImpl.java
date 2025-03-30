package com.example.calorieCalculator.service.impl;


import com.example.calorieCalculator.model.Dish;
import com.example.calorieCalculator.repository.DishRepository;
import com.example.calorieCalculator.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DishServiceImpl implements DishService {

    private DishRepository dishRepository;

    @Autowired
    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    @Transactional
    public void addDish(Dish dish){
        dishRepository.addDish(dish);
    };
}
