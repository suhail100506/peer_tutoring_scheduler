package com.example.peertutoring.service;

import com.example.peertutoring.entity.Tutor;
import com.example.peertutoring.exception.ResourceNotFoundException;
import com.example.peertutoring.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for managing Tutor entities.
 */
@Service
@Transactional
public class TutorService {

    private final TutorRepository tutorRepository;

    @Autowired
    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    /**
     * Retrieves all tutors.
     * @return List of all tutors
     */
    public List<Tutor> getAllTutors() {
        return tutorRepository.findAll();
    }

    /**
     * Retrieves a tutor by ID.
     * @param id Tutor ID
     * @return the tutor with the given ID
     * @throws ResourceNotFoundException if no tutor is found with the given ID
     */
    public Tutor getTutorById(Long id) {
        return tutorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tutor not found with id: " + id));
    }

    /**
     * Saves a tutor.
     * @param tutor Tutor to save
     * @return the saved tutor
     */
    public Tutor saveTutor(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    /**
     * Deletes a tutor by ID.
     * @param id Tutor ID to delete
     * @throws ResourceNotFoundException if no tutor is found with the given ID
     */
    public void deleteTutor(Long id) {
        Tutor tutor = tutorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tutor not found with id: " + id));
        tutorRepository.delete(tutor);
    }
}
