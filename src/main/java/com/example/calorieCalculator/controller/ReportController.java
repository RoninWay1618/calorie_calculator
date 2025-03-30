package com.example.calorieCalculator.controller;

import com.example.calorieCalculator.model.Dish;
import com.example.calorieCalculator.model.Reports;
import com.example.calorieCalculator.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reportsApi")
public class ReportController {

    private ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // Отчет за день (сумма калорий)
    @GetMapping("/dailyReport/{userEmail}/{localDate}")
    public Reports getDailyReport(@PathVariable String userEmail, @PathVariable LocalDate localDate) {
        Reports reports = reportService.getDailyReport(userEmail, localDate);
        return reports;
    }

    @GetMapping("/checkDailyNorm/{userEmail}/{localDate}")
    public String checkDailyNorm(@PathVariable String userEmail, @PathVariable LocalDate localDate){
        boolean bool = reportService.checkDailyNorm(userEmail, localDate);
        if(bool){
            return "Yes";
        } else {
            return "No";
        }
    }

    @GetMapping("/history/{userEmail}/{localDate}")
    public List<Dish> getHistory(@PathVariable String userEmail, @PathVariable LocalDate localDate) {
        return reportService.getHistory(userEmail, localDate);
    }
}
