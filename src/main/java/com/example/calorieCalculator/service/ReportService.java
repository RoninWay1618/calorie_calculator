package com.example.calorieCalculator.service;

import com.example.calorieCalculator.model.Dish;
import com.example.calorieCalculator.model.Reports;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    Reports getDailyReport(String userEmail, LocalDate date);
    boolean checkDailyNorm(String userEmail, LocalDate date);
    List<Dish> getHistory(String userEmail, LocalDate date);
}
