package com.example.peertutoring.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String subject;
    private String availability;

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL)
    private List<Session> sessions;
}