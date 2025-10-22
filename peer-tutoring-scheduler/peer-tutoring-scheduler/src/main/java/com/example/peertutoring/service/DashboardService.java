package com.example.peertutoring.service;

import com.example.peertutoring.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private TutorService tutorService;
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private SessionService sessionService;
    
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // Get counts
        long tutorCount = tutorService.getAllTutors().size();
        long studentCount = studentService.getAllStudents().size();
        
        // Get today's sessions
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        List<Session> todaySessions = sessionService.getSessionsInDateRange(
            today.atStartOfDay(),
            tomorrow.atStartOfDay()
        );
        
        // Get upcoming sessions (next 7 days)
        LocalDate nextWeek = today.plusWeeks(1);
        List<Session> upcomingSessions = sessionService.getSessionsInDateRange(
            tomorrow.atStartOfDay(),
            nextWeek.atStartOfDay()
        );
        
        // Prepare the response
        stats.put("tutorCount", tutorCount);
        stats.put("studentCount", studentCount);
        stats.put("todaySessions", todaySessions);
        stats.put("upcomingSessions", upcomingSessions);
        
        return stats;
    }
}
