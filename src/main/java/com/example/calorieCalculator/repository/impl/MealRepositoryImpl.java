package com.example.calorieCalculator.repository.impl;

import com.example.calorieCalculator.model.Dish;
import com.example.calorieCalculator.model.Meal;
import com.example.calorieCalculator.repository.MealRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MealRepositoryImpl implements MealRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveMeal(Meal meal) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(meal);
    }

    @Override
    public List<Dish> findByUserIdAndDate(String emailUser, LocalDate date) {
        Session session = sessionFactory.getCurrentSession();

                List<Dish> users = session.createQuery("from Meal"
                , Dish.class)
                .getResultList();

                return users;
    }
}
