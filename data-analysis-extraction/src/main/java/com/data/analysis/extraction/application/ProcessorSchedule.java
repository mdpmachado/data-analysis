package com.data.analysis.extraction.application;

import com.data.analysis.extraction.domain.service.ExtractDatFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProcessorSchedule {

    private final ExtractDatFileService extractDatFileService;

    public ProcessorSchedule(ExtractDatFileService extractDatFileService) {
        this.extractDatFileService = extractDatFileService;
    }

    @Scheduled(cron = "0 */2 * * * *")
    public void execute() {
        log.info("Starting Dat File processing engine...");

        try {

            extractDatFileService.execute();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        log.info("Dat file processed.");
    }
}
