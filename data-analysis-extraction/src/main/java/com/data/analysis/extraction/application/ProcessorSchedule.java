package com.data.analysis.extraction.application;

import com.data.analysis.extraction.domain.model.Acummulator;
import com.data.analysis.extraction.domain.service.ExtractDatFileService;
import com.data.analysis.extraction.infra.kafka.AbstractProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@Component
public class ProcessorSchedule extends AbstractProducer {

    public final static String EXTRACTED_DATA_TOPIC = "EXTRACTED_DATA_TOPIC";

    private final ExtractDatFileService extractDatFileService;

    public ProcessorSchedule(ExtractDatFileService extractDatFileService) {
        this.extractDatFileService = extractDatFileService;
    }

    @Scheduled(cron = "0 */2 * * * *")
    public void execute() {
        log.info("Starting Dat File processing engine...");

        try {

            List<Future<Acummulator>> batchInProcess = extractDatFileService.execute();
            CompletableFuture.allOf(batchInProcess.toArray(new CompletableFuture[0])).get();
            sendAcummulatorProcessed(batchInProcess);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("Dat file processed.");
    }

    private void sendAcummulatorProcessed(List<Future<Acummulator>> batchProcessed) throws Exception {
        List<Acummulator> acummulatorList = new LinkedList<>();
        batchProcessed.forEach(res -> {
            try {
                acummulatorList.add(res.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        send(EXTRACTED_DATA_TOPIC, acummulatorList);
    }
}
