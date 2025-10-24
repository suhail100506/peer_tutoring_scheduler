package com.example.peertutoring.controller;

import com.example.peertutoring.entity.Dashboard;
import com.example.peertutoring.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    
    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }
    
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        Dashboard dashboard = dashboardService.getDashboardStatistics();
        model.addAttribute("dashboard", dashboard);
        return "dashboard/index";
    }
}
