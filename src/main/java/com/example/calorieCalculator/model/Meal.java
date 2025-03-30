package com.example.calorieCalculator.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "meals")
@Data
@NoArgsConstructor
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private Integer id;

    // Связь с пользователем
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    // Список блюд
    // Один прием пищи (Meal) может содержать много блюд (Dish).
    // Одно блюдо (Dish) может присутствовать в многих приемах пищи (Meal).
    @ManyToMany
    @JoinTable(
            name = "meal_dishes",
            joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<Dish> items = new ArrayList<>();

    public Dish getDish(Dish dish) {
        for (Dish d : items) {
            if (d.equals(dish)) {
                return d;
            }
        }
        return null;
    }

}
