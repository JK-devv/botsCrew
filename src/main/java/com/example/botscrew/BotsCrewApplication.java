package com.example.botscrew;

import com.example.botscrew.service.BotsCrewService;
import com.example.botscrew.util.Constant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class BotsCrewApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BotsCrewApplication.class, args);
        BotsCrewService service = context.getBean(BotsCrewService.class);
        Scanner scanner = new Scanner(System.in);
        System.out.print(Constant.HELLO);
        boolean continueExecution = true;
        while (continueExecution) {
            try {
                System.out.print(Constant.ASK_QUESTION);
                String input = scanner.nextLine().trim();
                String[] inputArray = input.split(" ");

                if (input.startsWith(Constant.WHO_IS_HEAD_OF_DEPARTMENT)) {
                    System.out.println(service.getHeadOfDepartment(inputArray[inputArray.length - 1]));
                } else if (input.startsWith(Constant.SHOW_AVERAGE_SALARY)) {
                    System.out.println(service.getAverageDepartmentSalary(inputArray[inputArray.length - 1]));
                } else if (input.startsWith(Constant.SHOW_COUNT_OF_EMPLOYEE)) {
                    System.out.println(service.getEmployeeCount(inputArray[inputArray.length - 1]));
                } else if (input.startsWith(Constant.SHOW) && input.endsWith(Constant.STATISTICS)) {
                    System.out.println(service.getStatistic(inputArray[1]));
                } else if (input.startsWith(Constant.GLOBAL_SEARCH)) {
                    System.out.println(service.globalSearch(inputArray[inputArray.length - 1]));
                }

            } catch (RuntimeException exception) {
                System.err.println(Constant.SOMETHING_WRONG + exception.getMessage());
            } finally {
                System.out.println(Constant.DO_YOU_HAVE_ANOTHER_QUESTION);
                String response = scanner.nextLine().trim();
                continueExecution = response.equalsIgnoreCase(Constant.YES);
            }
        }
        System.out.println(Constant.BYE);
    }
}
