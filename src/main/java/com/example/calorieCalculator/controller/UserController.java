package com.example.calorieCalculator.controller;

import com.example.calorieCalculator.exception.UserException;
import com.example.calorieCalculator.model.User;
import com.example.calorieCalculator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usersApi")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok("Пользователь создан");
    }

    @GetMapping("/users/{email}")
    public User findByEmail(@PathVariable String email){
        User user = userService.findByEmail(email);

        if(user == null){
            throw new UserException("There is no user with email " + email
                    + " in the database");
        }

        return user;
    }
}
