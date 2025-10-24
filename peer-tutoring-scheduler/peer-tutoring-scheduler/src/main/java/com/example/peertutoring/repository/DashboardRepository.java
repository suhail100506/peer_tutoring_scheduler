package com.example.peertutoring.repository;

import com.example.peertutoring.entity.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardRepository extends JpaRepository<Dashboard, Long> {
    
    @Query("SELECT COUNT(s) FROM Student s")
    Long countTotalStudents();
    
    @Query("SELECT COUNT(t) FROM Tutor t WHERE t.isActive = true")
    Long countActiveTutors();
    
    @Query("SELECT COUNT(s) FROM Session s WHERE s.sessionDate >= CURRENT_DATE")
    Long countUpcomingSessions();
    
    @Query("SELECT AVG(f.rating) FROM Feedback f")
    Double getAverageSatisfactionRate();
}
