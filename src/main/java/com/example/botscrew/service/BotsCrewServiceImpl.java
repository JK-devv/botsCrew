package com.example.botscrew.service;

import com.example.botscrew.dto.Department;
import com.example.botscrew.dto.Lector;
import com.example.botscrew.exception.BotsCrewException;
import com.example.botscrew.repository.DepartmentRepository;
import com.example.botscrew.repository.LectorRepository;
import com.example.botscrew.util.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.botscrew.util.Constant.*;

@Service
@RequiredArgsConstructor
public class BotsCrewServiceImpl implements BotsCrewService {
    private final LectorRepository lectorRepository;
    private final DepartmentRepository departmentRepository;


    @Override
    public String getHeadOfDepartment(String departmentName) {
        String headOfDepartment = departmentRepository.findByDepartmentName(departmentName)
                .map(Department::getHeadOfDepartment)
                .orElseThrow(
                        () -> new BotsCrewException
                                (String.format(Constant.CANNOT_FIND_HEAD_OF_DEPARTMENT,departmentName)));
        return String.format(HEAD_OF_DEPARTMENT_IS, departmentName, headOfDepartment);
    }

    @Override
    public String getStatistic(String departmentName) {
        Map<String, Long> statistic = lectorRepository
                .findByDepartmentDepartmentName(departmentName)
                .stream()
                .collect(Collectors.groupingBy(Lector::getDegree, Collectors.counting()));
        if (statistic.isEmpty()) {
            throw new BotsCrewException(String.format(CANNOT_FIND_STATISTIC, departmentName));
        }
        return processingStatistic(statistic);
    }

    private String processingStatistic(Map<String, Long> statistic) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Long> entry : statistic.entrySet()) {
            result.append(entry.getKey())
                    .append(DELIMITER)
                    .append(entry.getValue())
                    .append(NEW_LINE);
        }
        return result.toString();
    }

    @Override
    public String getAverageDepartmentSalary(String departmentName) {
        double averageSalary = lectorRepository
                .findByDepartmentDepartmentName(departmentName)
                .stream()
                .mapToDouble(Lector::getSalary)
                .average()
                .orElseThrow(
                        () -> new BotsCrewException(
                                String.format(CANNOT_CALCULATE_SALARY, departmentName)));
        return String.format(AVERAGE_SALARY, departmentName, Math.round(averageSalary * 100.0) / 100.0);
    }

    @Override
    public int getEmployeeCount(String departmentName) {
        return departmentRepository.findByDepartmentName(departmentName)
                .orElseThrow(
                        () -> new BotsCrewException(
                                String.format(CANNOT_FIND_DEPARTMENT, departmentName)))
                .getEmployeeCount();
    }

    @Override
    public String globalSearch(String searchValue) {
        List<Lector> byNameContainingIgnoreCase =
                lectorRepository.findByNameContainingIgnoreCase(searchValue);

        return byNameContainingIgnoreCase.size() == 0 ? NO_LECTORS
                : byNameContainingIgnoreCase
                .stream()
                .map(Lector::getName)
                .collect(Collectors.joining(","));
    }
}
