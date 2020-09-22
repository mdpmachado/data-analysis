package com.data.analysis.transformation.application;

import com.data.analysis.transformation.domain.model.Acummulator;
import com.data.analysis.transformation.domain.service.TransformDatFileService;
import com.data.analysis.transformation.infra.constantes.Constantes;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class Processor {

    private final TransformDatFileService transformDatFileService;

    public Processor(TransformDatFileService transformDatFileService) {
        this.transformDatFileService = transformDatFileService;
    }

    @KafkaListener(topics = Constantes.EXTRACTED_DATA_TOPIC, groupId = Constantes.DATA_ANALYSIS_TRANSFORMATION_KAFKA)
    public void schemaCreated(String json) {
        log.info("Starting Data Transformation...");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            transformDatFileService.execute(Arrays.asList(objectMapper.readValue(json, Acummulator[].class)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        log.info("Data Transformed...");
    }
}
