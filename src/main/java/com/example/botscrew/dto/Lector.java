package com.example.botscrew.dto;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "lectors")
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Degree degree;
    private double salary;
    @OneToOne
    private Department department;
}
