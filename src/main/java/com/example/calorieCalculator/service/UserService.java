package com.example.calorieCalculator.service;

import com.example.calorieCalculator.model.User;

public interface UserService {


    User findByEmail(String email);

    void saveUser(User user);
}
