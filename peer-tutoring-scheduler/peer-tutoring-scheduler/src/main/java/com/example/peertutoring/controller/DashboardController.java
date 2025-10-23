package com.example.peertutoring.controller;

import com.example.peertutoring.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class DashboardController {
    
    @Autowired
    private DashboardService dashboardService;
    
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        // Get current admin's username and full name
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        String fullName = dashboardService.getAdminFullName(username);
        
        // Get dashboard data
        model.addAttribute("studentCount", dashboardService.getStudentCount());
        model.addAttribute("tutorCount", dashboardService.getTutorCount());
        model.addAttribute("upcomingSessions", dashboardService.getUpcomingSessionsCount());
        model.addAttribute("pendingAlerts", dashboardService.getPendingAlertsCount());
        model.addAttribute("recentSessions", dashboardService.getRecentSessions(5));
        model.addAttribute("username", username);
        model.addAttribute("fullName", fullName);
        
        return "admin/dashboard";
    }
}
