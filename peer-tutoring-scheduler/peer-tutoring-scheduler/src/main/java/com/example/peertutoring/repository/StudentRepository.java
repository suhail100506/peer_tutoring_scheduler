package com.example.peertutoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.peertutoring.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}