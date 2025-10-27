package com.example.peertutoring.repository;

import com.example.peertutoring.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    /**
     * Finds all sessions for a specific tutor.
     * @param tutorId The ID of the tutor
     * @return List of sessions for the given tutor
     */
    List<Session> findByTutorId(Long tutorId);
    
    /**
     * Finds all sessions for a specific student.
     * @param studentId The ID of the student
     * @return List of sessions for the given student
     */
    List<Session> findByStudentId(Long studentId);
    
    /**
     * Finds all sessions within a specific date range.
     * @param start Start date and time (inclusive)
     * @param end End date and time (exclusive)
     * @return List of sessions within the date range
     */
    List<Session> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}