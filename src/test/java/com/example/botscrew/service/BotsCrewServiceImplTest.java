package com.example.botscrew.service;

import com.example.botscrew.dto.Department;
import com.example.botscrew.dto.Lector;
import com.example.botscrew.exception.BotsCrewException;
import com.example.botscrew.repository.DepartmentRepository;
import com.example.botscrew.repository.LectorRepository;
import com.example.botscrew.util.Constant;
import com.example.botscrew.util.TestConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static com.example.botscrew.util.Constant.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles(profiles = "test")
class BotsCrewServiceImplTest {
    @Autowired
    private BotsCrewServiceImpl botsCrewService;
    @MockBean
    private LectorRepository lectorRepository;
    @MockBean
    private DepartmentRepository departmentRepository;

    @Test
    @DisplayName("should return head of department by department name")
    void getHeadOfDepartment_Ok() {

        Department department = Department.builder()
                .headOfDepartment(TestConstant.TEST_HEAD_OF_DEPARTMENT)
                .departmentName(TestConstant.FINANCE_DEPARTMENT)
                .build();

        when(departmentRepository.findByDepartmentName(TestConstant.FINANCE_DEPARTMENT))
                .thenReturn(Optional.of(department));

        String actual = botsCrewService
                .getHeadOfDepartment(TestConstant.FINANCE_DEPARTMENT);

        assertEquals(String.format(HEAD_OF_DEPARTMENT_IS,
                        TestConstant.FINANCE_DEPARTMENT,
                        TestConstant.TEST_HEAD_OF_DEPARTMENT),
                actual);

        verify(departmentRepository, times(1))
                .findByDepartmentName(TestConstant.FINANCE_DEPARTMENT);
    }

    @Test
    @DisplayName("throw exception when try to get head of "
            + "department by not existed department name")
    void shouldNotReturnHeadOfDepartmentForNotExistedDepartmentName_NotOk() {

        when(departmentRepository.findByDepartmentName(TestConstant.NOT_EXISTED_DEPARTMENT))
                .thenReturn(Optional.empty());

        assertThrows(BotsCrewException.class, () -> botsCrewService
                .getHeadOfDepartment(TestConstant.NOT_EXISTED_DEPARTMENT), (String.format(Constant.CANNOT_FIND_HEAD_OF_DEPARTMENT,
                TestConstant.NOT_EXISTED_DEPARTMENT)));

        verify(departmentRepository, times(1))
                .findByDepartmentName(TestConstant.NOT_EXISTED_DEPARTMENT);
    }

    @Test
    @DisplayName("should return statistic by department name")
    void getStatistic_Ok() {

        when(lectorRepository.findByDepartmentDepartmentName(
                TestConstant.MATHEMATICAL_DEPARTMENT))
                .thenReturn(initLectors());

        String actual = botsCrewService
                .getStatistic(TestConstant.MATHEMATICAL_DEPARTMENT);

        Assertions.assertEquals("""
                professor - 1
                assistant - 1
                associate professor - 1
                """, actual);

        verify(lectorRepository, times(1))
                .findByDepartmentDepartmentName(TestConstant.MATHEMATICAL_DEPARTMENT);
    }

    @Test
    @DisplayName("should throw exception by not existed department name")
    void shouldNotReturnStatisticForNotExistedDepartment_NotOk() {

        when(lectorRepository.findByDepartmentDepartmentName(
                TestConstant.NOT_EXISTED_DEPARTMENT))
                .thenReturn(List.of());

        assertThrows(BotsCrewException.class, () -> botsCrewService
                .getStatistic(TestConstant.NOT_EXISTED_DEPARTMENT), (String.format(CANNOT_FIND_STATISTIC, TestConstant.NOT_EXISTED_DEPARTMENT)));

        verify(lectorRepository, times(1))
                .findByDepartmentDepartmentName(TestConstant.NOT_EXISTED_DEPARTMENT);
    }

    @Test
    @DisplayName("should return average salary for department by department name")
    void getAverageDepartmentSalary_Ok() {
        when(lectorRepository
                .findByDepartmentDepartmentName(TestConstant.HUMANITARIAN_DEPARTMENT))
                .thenReturn(initLectors());

        String actual = botsCrewService
                .getAverageDepartmentSalary(TestConstant.HUMANITARIAN_DEPARTMENT);

        assertEquals(String.format(AVERAGE_SALARY, TestConstant.HUMANITARIAN_DEPARTMENT, 1143.01),
                actual);

        verify(lectorRepository, times(1))
                .findByDepartmentDepartmentName(TestConstant.HUMANITARIAN_DEPARTMENT);
    }

    @Test
    @DisplayName("should throw exception when department name does not exist")
    void shouldTNotReturnAverageSalaryForNotExistedDepartment_NotOk() {
        when(lectorRepository
                .findByDepartmentDepartmentName(TestConstant.NOT_EXISTED_DEPARTMENT))
                .thenReturn(List.of());

        assertThrows(BotsCrewException.class, () -> botsCrewService
                .getAverageDepartmentSalary(TestConstant.NOT_EXISTED_DEPARTMENT), String.format(CANNOT_CALCULATE_SALARY, TestConstant.NOT_EXISTED_DEPARTMENT));

        verify(lectorRepository, times(1))
                .findByDepartmentDepartmentName(TestConstant.NOT_EXISTED_DEPARTMENT);
    }

    @Test
    @DisplayName("should return count of employee in department")
    void getEmployeeCount() {

        Department department = Department.builder()
                .employeeCount(3)
                .departmentName(TestConstant.MATHEMATICAL_DEPARTMENT)
                .build();

        when(departmentRepository
                .findByDepartmentName(TestConstant.MATHEMATICAL_DEPARTMENT))
                .thenReturn(Optional.of(department));

        int actual = botsCrewService.getEmployeeCount(TestConstant.MATHEMATICAL_DEPARTMENT);
        assertEquals(3, actual);

        verify(departmentRepository, times(1))
                .findByDepartmentName(TestConstant.MATHEMATICAL_DEPARTMENT);
    }

    @Test
    @DisplayName("should throw an exception when we "
            + "ask for employee count by not existed department name")
    void shouldNotReturnEmployeeCountForNotExistedDepartmentName_NotOk() {
        when(departmentRepository
                .findByDepartmentName(TestConstant.NOT_EXISTED_DEPARTMENT))
                .thenReturn(Optional.empty());

        assertThrows(BotsCrewException.class, () -> botsCrewService.getEmployeeCount(TestConstant.NOT_EXISTED_DEPARTMENT), String.format(CANNOT_FIND_DEPARTMENT, TestConstant.NOT_EXISTED_DEPARTMENT));

        verify(departmentRepository, times(1))
                .findByDepartmentName(TestConstant.NOT_EXISTED_DEPARTMENT);
    }

    @Test
    @DisplayName("should search by param")
    void globalSearch_Ok() {
        when(lectorRepository.findByNameContainingIgnoreCase(TestConstant.SEARCH_PARAM))
                .thenReturn(initLectors());

        String actual = botsCrewService.globalSearch(TestConstant.SEARCH_PARAM);
        assertEquals("Olga Sarmativna,Hilarion Lubomarovych,Emmanuel Ruslanovych",actual);

        verify(lectorRepository,times(1))
                .findByNameContainingIgnoreCase(TestConstant.SEARCH_PARAM);

    }

    @Test
    @DisplayName("should return empty list for not suitable searh param ")
    void shouldReturnMessageThatLectorsDoesNotExistWithSuchParam_NotOk() {
        when(lectorRepository.findByNameContainingIgnoreCase(TestConstant.SEARCH_PARAM))
                .thenReturn(List.of());

        String actual = botsCrewService.globalSearch(TestConstant.SEARCH_PARAM);
        assertEquals(NO_LECTORS,actual);

        verify(lectorRepository,times(1))
                .findByNameContainingIgnoreCase(TestConstant.SEARCH_PARAM);

    }

    private List<Lector> initLectors() {
        Department department = Department.builder()
                .departmentName(TestConstant.MATHEMATICAL_DEPARTMENT)
                .build();

        Lector assistant = Lector.builder()
                .degree("assistant")
                .name("Emmanuel Ruslanovych")
                .department(department)
                .salary(123.01)
                .build();

        Lector professor = Lector.builder()
                .degree("professor")
                .name("Olga Sarmativna")
                .department(department)
                .salary(1203.01)
                .build();

        Lector associateProfessor = Lector.builder()
                .degree("associate professor")
                .name("Hilarion Lubomarovych")
                .department(department)
                .salary(2103.01)
                .build();
        return List.of(professor, associateProfessor, assistant);
    }
}