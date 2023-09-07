package com.example.botscrew.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "departments")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "department_name")
    private String departmentName;
    @Column(name = "head_of_department")
    private String headOfDepartment;
    @Column(name = "employee_count")
    private int employeeCount;
}
