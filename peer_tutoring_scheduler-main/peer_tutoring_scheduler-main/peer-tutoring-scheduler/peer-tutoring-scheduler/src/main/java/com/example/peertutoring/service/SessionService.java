package com.example.peertutoring.service;

import com.example.peertutoring.entity.Session;
import com.example.peertutoring.exception.ResourceNotFoundException;
import com.example.peertutoring.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service class for managing Session entities.
 */
@Service
@Transactional
public class SessionService {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    /**
     * Retrieves all sessions.
     * @return List of all sessions
     */
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    /**
     * Retrieves a session by ID.
     * @param id Session ID
     * @return the session with the given ID
     * @throws ResourceNotFoundException if no session is found with the given ID
     */
    public Session getSessionById(Long id) {
        return sessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + id));
    }

    /**
     * Saves a session.
     * @param session Session to save
     * @return the saved session
     */
    public Session saveSession(Session session) {
        // Add any business logic/validation here if needed
        return sessionRepository.save(session);
    }

    /**
     * Deletes a session by ID.
     * @param id Session ID to delete
     * @throws ResourceNotFoundException if no session is found with the given ID
     */
    public void deleteSession(Long id) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + id));
        sessionRepository.delete(session);
    }

    /**
     * Retrieves all sessions for a specific tutor.
     * @param tutorId Tutor ID
     * @return List of sessions for the given tutor
     */
    public List<Session> getSessionsByTutor(Long tutorId) {
        return sessionRepository.findByTutorId(tutorId);
    }

    /**
     * Retrieves all sessions for a specific student.
     * @param studentId Student ID
     * @return List of sessions for the given student
     */
    public List<Session> getSessionsByStudent(Long studentId) {
        return sessionRepository.findByStudentId(studentId);
    }

    /**
     * Retrieves all sessions within a specific date range.
     * @param start Start date and time (inclusive)
     * @param end End date and time (exclusive)
     * @return List of sessions within the specified date range
     */
    public List<Session> getSessionsInDateRange(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Start and end dates cannot be null");
        }
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
        return sessionRepository.findByStartTimeBetween(start, end);
    }
}
