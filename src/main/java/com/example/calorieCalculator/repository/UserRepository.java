package com.example.calorieCalculator.repository;

import com.example.calorieCalculator.model.User;

public interface UserRepository{

    // Поиск пользователя по email
    User findByEmail(String email);

    void saveUser(User user);

}
