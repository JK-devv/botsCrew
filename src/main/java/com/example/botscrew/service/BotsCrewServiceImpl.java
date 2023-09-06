package com.example.botscrew.service;

import com.example.botscrew.dto.Department;
import com.example.botscrew.repository.DepartmentRepository;
import com.example.botscrew.repository.LectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BotsCrewServiceImpl implements BotsCrewService {
    private final LectorRepository lectorRepository;
    private final DepartmentRepository departmentRepository;


    @Override
    public String getHeadOfDepartment(String departmentName) {
         return departmentRepository.findByDepartmentName(departmentName)
                 .map(Department::getHeadOfDepartment)
                 .orElseThrow(
                         () -> new RuntimeException
                                 ("Can not find head of department by departmnet name: " + departmentName));
    }

    @Override
    public String getStatistic(String departmentName) {
        //User Input: Show {department_name} statistics.
        //           Answer: assistans - {assistams_count}.
        //        associate professors - {associate_professors_count}
        //        professors -{professors_count}
        lectorRepository.findByDepartmentContains(departmentName);

        return null;
    }

    @Override
    public double getAverageDepartmentSalary(String departmentName) {
        return 0;
    }

    @Override
    public int getEmployeeCount(String departmentName) {
        return 0;
    }

    @Override
    public List<String> globalSearch(String departmentName) {
        return null;
    }
}
