package com.example.calorieCalculator.model;

import com.example.calorieCalculator.goal.Goal;
import com.example.calorieCalculator.repository.impl.UserRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertEquals;

class UserRepositoryImplTest {
    @Test
    void testSaveUserCalculatesDailyCalorieNormForMaintenance() {
        // Создаем мок Session и SessionFactory
        Session session = Mockito.mock(Session.class);
        SessionFactory sessionFactory = Mockito.mock(SessionFactory.class);
        Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);

        // Создаем реализацию репозитория, подставляя мок SessionFactory
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        userRepository.setSessionFactory(sessionFactory); // предполагается, что добавлен сеттер

        // Подготавливаем тестового пользователя для поддержания массы
        User user = new User("Ivan", "ivan@example.com", 30, 75.0, 180.0, Goal.MAINTENANCE);

        // Вызываем метод сохранения
        userRepository.saveUser(user);

        // Расчет по формуле для мужчин:
        // BMR = 88.36 + (13.4 * weight) + (4.8 * height) - (5.7 * age)
        double expectedBMR = 88.36 + (13.4 * 75.0) + (4.8 * 180.0) - (5.7 * 30);
        assertEquals(expectedBMR, user.getDailyCalorieNorm(), 0.01);
    }
}
