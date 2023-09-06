package com.example.botscrew;

import com.example.botscrew.service.BotsCrewServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BotsCrewApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BotsCrewApplication.class, args);
        BotsCrewServiceImpl myService = context.getBean(BotsCrewServiceImpl.class);
     //   SpringApplication.run(BotsCrewApplication.class, args);

        String finance = myService.getStatistic("Finance");
        System.out.println(finance);
    }
}
