package com.example.calorieCalculator.service;

import com.example.calorieCalculator.model.Dish;
import com.example.calorieCalculator.model.Meal;

import java.time.LocalDate;
import java.util.List;

public interface MealService {
    void saveMeal(String email, Meal meal);
    List<Dish> findByUserIdAndDate(String emailUser, LocalDate date);
}
