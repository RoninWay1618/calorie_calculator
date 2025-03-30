package com.example.calorieCalculator.repository.impl;

import com.example.calorieCalculator.model.User;
import com.example.calorieCalculator.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public User findByEmail(String email){
        Session session = sessionFactory.getCurrentSession();

        User user = session.get(User.class, email);

        return user;
    }

    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        // Расчет дневной нормы калорий по формуле Харриса-Бенедикта
        // Пример для мужчин
        double bmr = 88.36 + (13.4 * user.getWeight()) + (4.8 * user.getHeight()) - (5.7 * user.getAge());

        switch (user.getGoal()) {
            case LOSS:
                bmr *= 0.85;
                break;
            case GAIN:
                bmr *= 1.15;
                break;
            case MAINTENANCE:
            default:
                break;
        }
        user.setDailyCalorieNorm(bmr);
        session.saveOrUpdate(user);
    }
}
