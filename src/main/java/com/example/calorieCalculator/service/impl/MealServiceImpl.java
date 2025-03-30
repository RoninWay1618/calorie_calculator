package com.example.calorieCalculator.service.impl;


import com.example.calorieCalculator.model.Dish;
import com.example.calorieCalculator.model.Meal;
import com.example.calorieCalculator.model.User;
import com.example.calorieCalculator.repository.MealRepository;
import com.example.calorieCalculator.repository.UserRepository;
import com.example.calorieCalculator.repository.impl.MealRepositoryImpl;
import com.example.calorieCalculator.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class MealServiceImpl implements MealService {

    private UserRepository userRepository;
    private MealRepository mealRepository;

    @Autowired
    public MealServiceImpl (UserRepository userRepository) {
        this.userRepository = userRepository;
        this.mealRepository = new MealRepositoryImpl();
    }

    @Override
    @Transactional
    public void saveMeal(String userEmail, Meal meal) {
        // Получаем пользователя по Email
        User user = userRepository.findByEmail(userEmail);

        // Связываем прием пищи с найденным пользователем
        meal.setUser(user);

        // Если дата не установлена, устанавливаем текущую дату
        if (meal.getDate() == null) {
            meal.setDate(LocalDate.now());
        }

        mealRepository.saveMeal(meal);
    }

    @Override
    public List<Dish> findByUserIdAndDate(String emailUser, LocalDate date) {
        return mealRepository.findByUserIdAndDate(emailUser, date);
    }
}
