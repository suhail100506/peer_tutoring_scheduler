package com.example.peertutoring.service;

import com.example.peertutoring.entity.Admin;
import com.example.peertutoring.exception.ResourceNotFoundException;
import com.example.peertutoring.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Admin entities.
 */
@Service
@Transactional
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    /**
     * Retrieves all admins.
     * @return List of all admins
     */
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    /**
     * Retrieves an admin by ID.
     * @param id Admin ID
     * @return the admin with the given ID
     * @throws ResourceNotFoundException if no admin is found with the given ID
     */
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + id));
    }

    /**
     * Saves an admin. Encodes the password before saving.
     * @param admin Admin to save
     * @return the saved admin
     */
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    /**
     * Deletes an admin by ID.
     * @param id Admin ID to delete
     * @throws ResourceNotFoundException if no admin is found with the given ID
     */
    public void deleteAdmin(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + id));
        adminRepository.delete(admin);
    }

    /**
     * Finds an admin by username.
     * @param username Username to search for
     * @return Optional containing the admin if found
     */
    public Optional<Admin> findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    /**
     * Finds an admin by email.
     * @param email Email to search for
     * @return Optional containing the admin if found
     */
    public Optional<Admin> findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
}
