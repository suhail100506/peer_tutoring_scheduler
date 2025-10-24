package com.example.peertutoring.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface DashboardRepository {
    Long countTotalStudents();
    Long countActiveTutors();
    Long countUpcomingSessions();
    Double getAverageSatisfactionRate();
}
