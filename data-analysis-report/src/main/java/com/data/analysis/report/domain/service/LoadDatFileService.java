package com.data.analysis.report.domain.service;

import com.data.analysis.report.infra.constantes.Constantes;
import com.data.analysis.report.domain.model.IndicatorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class LoadDatFileService {

    @Value("${app.data-directory.out}")
    private String outDirectory;

    public void execute(IndicatorResult indicatorResult) throws IOException {
        log.info("Load results on {}.", outDirectory);
        log.info("Count salesmen {}.", indicatorResult.getCountSalesmen());
        log.info("Count clients {}.", indicatorResult.getCountClients());
        log.info("Best sale ID {}.", indicatorResult.getBestSale());
        log.info("Worse salesman {}.", indicatorResult.getWorseSalesman());

        Path resultFile = Paths.get(outDirectory + "/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".done.dat");

        try (BufferedWriter writer = Files.newBufferedWriter(resultFile)) {
            writer.write("Count salesmen: " + indicatorResult.getCountSalesmen() + Constantes.NEW_LINE);
            writer.write("Count clients: " + indicatorResult.getCountClients() + Constantes.NEW_LINE);
            writer.write("Best sale ID: " + indicatorResult.getBestSale() + Constantes.NEW_LINE);
            writer.write("Worse salesman: " + indicatorResult.getWorseSalesman() + Constantes.NEW_LINE);
        }
    }

}
