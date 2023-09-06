package com.example.botscrew.service;

import java.util.List;

public interface BotsCrewService {
    String getHeadOfDepartment(String departmentName);
    String getStatistic (String departmentName);
    double getAverageDepartmentSalary(String departmentName);
    int getEmployeeCount(String departmentName);

    List<String> globalSearch(String departmentName);

}
