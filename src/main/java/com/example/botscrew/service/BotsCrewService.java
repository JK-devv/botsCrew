package com.example.botscrew.service;

public interface BotsCrewService {
    String getHeadOfDepartment(String departmentName);

    String getStatistic(String departmentName);

    String getAverageDepartmentSalary(String departmentName);

    int getEmployeeCount(String departmentName);

    String globalSearch(String searchValue);

}
