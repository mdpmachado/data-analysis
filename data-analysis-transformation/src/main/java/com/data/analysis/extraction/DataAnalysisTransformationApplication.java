package com.data.analysis.extraction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class DataAnalysisTransformationApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataAnalysisTransformationApplication.class, args);
    }
}
