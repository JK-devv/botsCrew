package com.example.botscrew.dto;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "lectors")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String degree;
    private double salary;
    @OneToOne
    private Department department;
}
