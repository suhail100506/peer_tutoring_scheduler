package com.example.peertutoring.controller;

import com.example.peertutoring.service.SessionService;
import com.example.peertutoring.service.StudentService;
import com.example.peertutoring.service.TutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;

@Controller
public class DashboardController {
    
    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);
    
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
        try {
            // Add counts for the dashboard cards
            model.addAttribute("sessionCount", sessionService.getAllSessions().size());
            model.addAttribute("tutorCount", tutorService.getAllTutors().size());
            model.addAttribute("studentCount", studentService.getAllStudents().size());
            model.addAttribute("subjectCount", 5); // Default value, adjust as needed
            
            // Add upcoming sessions (limited to 5 for the dashboard)
            model.addAttribute("upcomingSessions", 
                sessionService.getAllSessions().stream()
                    .limit(5)
                    .toList());
            
            return "dashboard/new-dashboard";
        } catch (Exception e) {
            logger.error("Error loading dashboard", e);
            model.addAttribute("error", "Error loading dashboard: " + e.getMessage());
            model.addAttribute("upcomingSessions", Collections.emptyList());
            return "dashboard/new-dashboard";
        }
    }
    
    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        logger.error("Request: " + req.getRequestURL() + " raised " + ex);
        
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }
}
