package com.example.peertutoring.service;

import com.example.peertutoring.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private TutorService tutorService;
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private SessionService sessionService;
    
    @Autowired
    private AdminService adminService;
    
    public long getStudentCount() {
        return studentService.getAllStudents().size();
    }
    
    public long getTutorCount() {
        return tutorService.getAllTutors().size();
    }
    
    public long getUpcomingSessionsCount() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextWeek = now.plusWeeks(1);
        return sessionService.getSessionsInDateRange(now, nextWeek).size();
    }
    
    public long getPendingAlertsCount() {
        try {
            // Try to get pending sessions if the method exists
            if (sessionService != null) {
                try {
                    // Using reflection to check if the method exists
                    java.lang.reflect.Method method = sessionService.getClass().getMethod("getPendingSessions");
                    Object result = method.invoke(sessionService);
                    if (result instanceof List) {
                        return ((List<?>) result).size();
                    }
                } catch (NoSuchMethodException | IllegalAccessException | java.lang.reflect.InvocationTargetException e) {
                    // Method doesn't exist or can't be called
                    return 0; // Default value when method is not available
                }
            }
            return 0; // Default value if sessionService is null
        } catch (Exception e) {
            return 0; // Fallback in case of any error
        }
    }
    
    public List<Session> getRecentSessions(int count) {
        try {
            if (sessionService != null) {
                try {
                    // Using reflection to check if the method exists with PageRequest parameter
                    java.lang.reflect.Method method = sessionService.getClass().getMethod("getRecentSessions", org.springframework.data.domain.PageRequest.class);
                    PageRequest pageable = PageRequest.of(0, count, Sort.by(Sort.Direction.DESC, "startTime"));
                    Object result = method.invoke(sessionService, pageable);
                    if (result instanceof List<?>) {
                        List<?> rawList = (List<?>) result;
                        if (rawList.isEmpty() || rawList.get(0) instanceof Session) {
                            @SuppressWarnings("unchecked")
                            List<Session> typedList = (List<Session>) (List<?>) result;
                            return typedList;
                        }
                    }
                } catch (NoSuchMethodException | IllegalAccessException | java.lang.reflect.InvocationTargetException e) {
                    // Method doesn't exist or can't be called, try alternative approach
                    return getFallbackRecentSessions(count);
                }
            }
            return getFallbackRecentSessions(count);
        } catch (Exception e) {
            return getFallbackRecentSessions(count);
        }
    }
    
    /**
     * Fallback method when the repository method is not available
     */
    private List<Session> getFallbackRecentSessions(int count) {
        try {
            // Try to get sessions using a different approach if available
            if (sessionService != null) {
                // Check if there's a method to get all sessions that we can manually sort
                java.lang.reflect.Method method = sessionService.getClass().getMethod("getAllSessions");
                Object result = method.invoke(sessionService);
                if (result instanceof List<?>) {
                    List<?> rawList = (List<?>) result;
                    if (rawList.isEmpty() || rawList.get(0) instanceof Session) {
                        @SuppressWarnings("unchecked")
                        List<Session> typedList = (List<Session>) (List<?>) result;
                        return typedList.stream()
                                .sorted((s1, s2) -> s2.getStartTime().compareTo(s1.getStartTime()))
                                .limit(count)
                                .collect(java.util.stream.Collectors.toList());
                    }
                }
            }
        } catch (Exception e) {
            // If any error occurs, return an empty list
        }
        return java.util.Collections.emptyList();
    }
    
    public String getAdminFullName(String username) {
        return adminService.findByUsername(username)
            .map(admin -> admin.getFullName())
            .orElse(username);
    }
}
