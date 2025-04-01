package com.example.calorieCalculator.repository;

import com.example.calorieCalculator.goal.Goal;
import com.example.calorieCalculator.model.Dish;
import com.example.calorieCalculator.model.Reports;
import com.example.calorieCalculator.model.User;
import com.example.calorieCalculator.service.impl.ReportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

class ReportServiceImplTest {

    private MealRepository mealRepository;
    private UserRepository userRepository;
    private ReportServiceImpl reportService;

    @BeforeEach
    void setUp() {
        mealRepository = Mockito.mock(MealRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        reportService = new ReportServiceImpl(mealRepository, userRepository);
    }

    @Test
    void testGetDailyReportCalculatesTotalCalories() {
        String email = "ivan@example.com";
        LocalDate date = LocalDate.of(2023, 6, 30);

        // Создаем тестовые блюда
        Dish dish1 = new Dish("Овсянка", 150.0, 5.0, 3.0, 27.0);
        Dish dish2 = new Dish("Яблоко", 80.0, 0.5, 0.3, 21.0);
        List<Dish> dishes = Arrays.asList(dish1, dish2);

        // Мокаем поведение репозитория
        Mockito.when(mealRepository.findByUserIdAndDate(email, date)).thenReturn(dishes);

        Reports report = reportService.getDailyReport(email, date);

        double expectedTotalCalories = 150.0 + 80.0;
        assertEquals(expectedTotalCalories, report.getTotalCalories(), 0.01);
        assertEquals(dishes, report.getMeals());
    }

    @Test
    void testCheckDailyNorm() {
        String email = "ivan@example.com";
        LocalDate date = LocalDate.of(2023, 6, 30);

        // Тестовые блюда
        Dish dish = new Dish("Овсянка", 150.0, 5.0, 3.0, 27.0);
        List<Dish> dishes = Collections.singletonList(dish);

        // Мокаем получение приемов пищи
        Mockito.when(mealRepository.findByUserIdAndDate(email, date)).thenReturn(dishes);

        // Тестовый пользователь с дневной нормой калорий
        User user = new User("Ivan", email, 30, 75.0, 180.0, Goal.MAINTENANCE);
        // Предположим, что дневная норма рассчитана равной 2000 калориям
        user.setDailyCalorieNorm(2000.0);
        Mockito.when(userRepository.findByEmail(email)).thenReturn(user);

        boolean withinNorm = reportService.checkDailyNorm(email, date);
        // Так как всего потреблено 150 калорий, условие должно выполниться
        assertTrue(withinNorm);
    }
}
