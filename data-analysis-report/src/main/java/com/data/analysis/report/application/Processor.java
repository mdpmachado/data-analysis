package com.data.analysis.report.application;

import com.data.analysis.report.domain.model.IndicatorResult;
import com.data.analysis.report.domain.service.LoadDatFileService;
import com.data.analysis.report.infra.kafka.AbstractProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class Processor extends AbstractProducer {

    public final static String TRANSFORMED_DATA_TOPIC = "TRANSFORMED_DATA_TOPIC";
    public static final String DATA_ANALYSIS_REPORT_KAFKA = "data-analysis-report-kafka";

    private final LoadDatFileService loadDatFileService;

    public Processor(LoadDatFileService loadDatFileService) {
        this.loadDatFileService = loadDatFileService;
    }

    @KafkaListener(topics = Processor.TRANSFORMED_DATA_TOPIC, groupId = DATA_ANALYSIS_REPORT_KAFKA)
    public void schemaCreated(String json) {
        log.info("Starting Report Generation...");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            loadDatFileService.execute(objectMapper.readValue(json, IndicatorResult.class));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        log.info("Report Generated...");
    }
}
