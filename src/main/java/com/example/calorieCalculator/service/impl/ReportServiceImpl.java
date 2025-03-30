package com.example.calorieCalculator.service.impl;

import com.example.calorieCalculator.model.Dish;
import com.example.calorieCalculator.model.Meal;
import com.example.calorieCalculator.model.Reports;
import com.example.calorieCalculator.model.User;
import com.example.calorieCalculator.repository.MealRepository;
import com.example.calorieCalculator.repository.UserRepository;
import com.example.calorieCalculator.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {

    private MealRepository mealRepository;
    private UserRepository userRepository;

    @Autowired
    public ReportServiceImpl(MealRepository mealRepository, UserRepository userRepository) {
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
    }

    // Формирование отчета за день с суммой калорий и списком приёмов пищи
    @Override
    @Transactional
    public Reports getDailyReport(String userEmail, LocalDate date) {
        List<Dish> meals = mealRepository.findByUserIdAndDate(userEmail, date);
        double totalCalories = 0;

        // Подсчет калорий по каждому блюду в приёмах пищи
        for (Dish dish : meals) {
                totalCalories += dish.getCaloriesPerServing();
        }

        return new Reports(totalCalories, meals);
    }

    // Проверка, укладывается ли пользователь в дневную норму калорий
    @Override
    @Transactional
    public boolean checkDailyNorm(String userEmail, LocalDate date) {
        Reports report = getDailyReport(userEmail, date);
        User user = userRepository.findByEmail(userEmail);
        return report.getTotalCalories() <= user.getDailyCalorieNorm();
    }

    // Получение истории питания по дням
    @Override
    @Transactional
    public List<Dish> getHistory(String userEmail, LocalDate date) {
        return mealRepository.findByUserIdAndDate(userEmail, date);
    }

}
