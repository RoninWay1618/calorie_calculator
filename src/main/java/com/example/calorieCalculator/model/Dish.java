package com.example.calorieCalculator.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "dishes")
@Data
@NoArgsConstructor
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "calories_per_serving", nullable = false)
    private Double caloriesPerServing;

    @Column(name = "proteins", nullable = false)
    private Double proteins;

    @Column(name = "fats", nullable = false)
    private Double fats;

    @Column(name = "carbs", nullable = false)
    private Double carbs;

    public Dish(String name, Double caloriesPerServing, Double proteins, Double fats, Double carbs) {
        this.name = name;
        this.caloriesPerServing = caloriesPerServing;
        this.proteins = proteins;
        this.fats = fats;
        this.carbs = carbs;
    }
}
