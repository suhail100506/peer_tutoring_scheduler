package com.example.peertutoring.controller;

import com.example.peertutoring.service.SessionService;
import com.example.peertutoring.service.StudentService;
import com.example.peertutoring.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    
    private final SessionService sessionService;
    private final TutorService tutorService;
    private final StudentService studentService;

    @Autowired
    public DashboardController(SessionService sessionService, 
                             TutorService tutorService,
                             StudentService studentService) {
        this.sessionService = sessionService;
        this.tutorService = tutorService;
        this.studentService = studentService;
    }
    
    @GetMapping({"/", "/dashboard"})
    public String showDashboard(Model model) {
        // Add counts for the dashboard cards
        model.addAttribute("sessionCount", sessionService.getAllSessions().size());
        model.addAttribute("tutorCount", tutorService.getAllTutors().size());
        model.addAttribute("studentCount", studentService.getAllStudents().size());
        model.addAttribute("subjectCount", 5); // Default value, adjust as needed
        
        // Add upcoming sessions (limited to 5 for the dashboard)
        model.addAttribute("upcomingSessions", 
            sessionService.getAllSessions().stream().limit(5).toList());
        
        return "dashboard/new-dashboard";
    }
}
