package com.example.botscrew.dto;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "department_name")
    private String departmentName;
    @Column(name = "head_of_department")
    private String headOfDepartment;
    @Column(name = "employee_count")
    private int employeeCount;
}
