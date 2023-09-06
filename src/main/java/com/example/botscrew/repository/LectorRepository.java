package com.example.botscrew.repository;

import com.example.botscrew.dto.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Integer> {
    List<Lector> findByDepartmentContains(String departmentName);
}
