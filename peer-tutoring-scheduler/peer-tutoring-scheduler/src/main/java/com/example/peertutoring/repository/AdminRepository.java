package com.example.peertutoring.repository;

import com.example.peertutoring.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    /**
     * Finds an admin by username.
     * @param username The username to search for
     * @return Optional containing the admin if found
     */
    Optional<Admin> findByUsername(String username);
    
    /**
     * Finds an admin by email.
     * @param email The email to search for
     * @return Optional containing the admin if found
     */
    Optional<Admin> findByEmail(String email);
}