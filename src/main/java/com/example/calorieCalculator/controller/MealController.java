package com.example.calorieCalculator.controller;

import com.example.calorieCalculator.model.Dish;
import com.example.calorieCalculator.model.Meal;
import com.example.calorieCalculator.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/mealsApi")
public class MealController {

    private MealService mealService;

    @Autowired
    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping("/meals/{userEmail}")
    public ResponseEntity<String> saveMeal(@PathVariable String userEmail, @RequestBody Meal meal) {
        mealService.saveMeal(userEmail, meal);
        return ResponseEntity.ok("Прием пищи добавлен");
    }

    @GetMapping("/meals/{userEmail}/{localDate}")
    public List<Dish> getMeal(@PathVariable String userEmail, @PathVariable LocalDate localDate) {
        List<Dish> dishes =  mealService.findByUserIdAndDate(userEmail, localDate);

        return dishes;
    }
}
