package com.example.calorieCalculator.repository.impl;


import com.example.calorieCalculator.model.Dish;
import com.example.calorieCalculator.repository.DishRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DishRepositoryImpl implements DishRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addDish(Dish dish) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(dish);
    }
}
