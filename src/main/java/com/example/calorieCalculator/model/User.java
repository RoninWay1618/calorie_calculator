package com.example.calorieCalculator.model;

import com.example.calorieCalculator.goal.Goal;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @NotBlank(message = "Имя не должно быть пустым")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Email не должен быть пустым")
    @Email(message = "Неверный формат email")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotNull(message = "Возраст обязателен")
    @Min(value = 10, message = "Возраст должен быть не менее 10 лет")
    @Column(name = "age", nullable = false)
    private Integer age;

    @NotNull(message = "Вес обязателен")
    @DecimalMin(value = "30.0", message = "Вес должен быть не менее 30 кг")
    @DecimalMax(value = "300.0", message = "Вес не может превышать 300 кг")
    @Column(name = "weight", nullable = false)
    private Double weight;

    @NotNull(message = "Рост обязателен")
    @DecimalMin(value = "50.0", message = "Рост должен быть не менее 50 см")
    @DecimalMax(value = "250.0", message = "Рост не может превышать 250 см")
    @Column(name = "height", nullable = false)
    private Double height;

    @NotNull(message = "Цель должна быть указана")
    @Enumerated(EnumType.STRING)
    @Column(name = "goal", nullable = false)
    private Goal goal;

    @Column(name = "daily_calorie_norm")
    private double dailyCalorieNorm;

    public User(String name, String email, Integer age, Double weight, Double height, Goal goal) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.goal = goal;
    }
}
