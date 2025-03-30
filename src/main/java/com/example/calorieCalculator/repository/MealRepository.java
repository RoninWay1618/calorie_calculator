package com.example.calorieCalculator.repository;

import com.example.calorieCalculator.model.Dish;
import com.example.calorieCalculator.model.Meal;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository {

    void saveMeal(Meal meal);

    List<Dish> findByUserIdAndDate(String emailUser, LocalDate date);
}
