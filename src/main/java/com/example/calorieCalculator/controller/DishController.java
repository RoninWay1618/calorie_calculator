package com.example.calorieCalculator.controller;

import com.example.calorieCalculator.model.Dish;
import com.example.calorieCalculator.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dishesApi")
public class DishController {

    private DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping("/dishes")
    public ResponseEntity<String> addDish(@RequestBody Dish dish){
        dishService.addDish(dish);
        return ResponseEntity.ok("Блюдо добавлено");
    }
}
