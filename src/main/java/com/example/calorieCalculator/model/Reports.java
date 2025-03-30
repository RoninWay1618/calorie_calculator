package com.example.calorieCalculator.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reports {

    private LocalDate date;
    private double totalCalories;
    private List<Dish> meals;
    private boolean withinCalorieNorm;

    // История питания по дням (ключ – дата, значение – список приёмов пищи)
    private Map<LocalDate, List<Meal>> history;

    public Reports (double totalCalories, List<Dish> meals){
        this.totalCalories = totalCalories;
        this.meals = meals;
    }


}
