package com.example.peertutoring.repository.impl;

import com.example.peertutoring.repository.DashboardRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class DashboardRepositoryImpl implements DashboardRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long countTotalStudents() {
        return entityManager.createQuery("SELECT COUNT(s) FROM Student s", Long.class).getSingleResult();
    }

    @Override
    public Long countActiveTutors() {
        return entityManager.createQuery("SELECT COUNT(t) FROM Tutor t WHERE t.isActive = true", Long.class).getSingleResult();
    }

    @Override
    public Long countUpcomingSessions() {
        return entityManager.createQuery("SELECT COUNT(s) FROM Session s WHERE s.startTime >= CURRENT_DATE", Long.class).getSingleResult();
    }

    @Override
    public Double getAverageSatisfactionRate() {
        return entityManager.createQuery("SELECT AVG(f.rating) FROM Feedback f", Double.class).getSingleResult();
    }
}
