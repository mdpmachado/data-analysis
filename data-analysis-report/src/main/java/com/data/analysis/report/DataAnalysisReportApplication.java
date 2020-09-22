package com.data.analysis.report;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class DataAnalysisReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataAnalysisReportApplication.class, args);
    }
}
