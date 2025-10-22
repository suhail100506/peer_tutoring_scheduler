package com.example.peertutoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.peertutoring.entity.Tutor;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
}