package com.data.analysis.extraction.application;

import com.data.analysis.extraction.domain.model.Acummulator;
import com.data.analysis.extraction.domain.model.IndicatorResult;
import com.data.analysis.extraction.domain.service.TransformDatFileService;
import com.data.analysis.extraction.infra.kafka.AbstractProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class Processor extends AbstractProducer {

    public final static String EXTRACTED_DATA_TOPIC = "EXTRACTED_DATA_TOPIC";
    public final static String TRANSFORMED_DATA_TOPIC = "TRANSFORMED_DATA_TOPIC";
    public static final String DATA_ANALYSIS_TRANSFORMATION_KAFKA = "data-analysis-transformation-kafka";

    private final TransformDatFileService transformDatFileService;

    public Processor(TransformDatFileService transformDatFileService) {
        this.transformDatFileService = transformDatFileService;
    }

    @KafkaListener(topics = Processor.EXTRACTED_DATA_TOPIC, groupId = DATA_ANALYSIS_TRANSFORMATION_KAFKA)
    public void schemaCreated(String json) {
        log.info("Starting Data Transformation...");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            IndicatorResult indicatorResult = transformDatFileService.execute(Arrays.asList(objectMapper.readValue(json, Acummulator[].class)));
            send(TRANSFORMED_DATA_TOPIC, indicatorResult);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        log.info("Data Transformed...");
    }
}
