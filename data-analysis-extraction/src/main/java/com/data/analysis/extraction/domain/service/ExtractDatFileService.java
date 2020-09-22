package com.data.analysis.extraction.domain.service;

import com.data.analysis.extraction.domain.enumeration.DataType;
import com.data.analysis.extraction.domain.model.Acummulator;
import com.data.analysis.extraction.domain.model.SalesmanResult;
import com.data.analysis.extraction.infra.parser.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ExtractDatFileService {

    @Value("${app.data-directory.in}")
    private String inDirectory;

    private final MessageSendingService messageSendingService;

    public ExtractDatFileService(MessageSendingService messageSendingService) {
        this.messageSendingService = messageSendingService;
    }

    public void execute() throws Exception {
        log.info("Loading files from {}.", inDirectory);
        List<Future<Acummulator>> batchInProcess = new ArrayList<>();
        Files.list(Paths.get(inDirectory)).forEach(path ->batchInProcess.add(read(path)));

        CompletableFuture.allOf(batchInProcess.toArray(new CompletableFuture[0])).get();
        List<Acummulator> accumulatorList = getAccumulatorList(batchInProcess);
        messageSendingService.send(accumulatorList);
    }

    private Future<Acummulator> read(Path path) {
        return CompletableFuture.supplyAsync(() -> {
            Acummulator acummulator = Acummulator.builder().build();

            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path.toFile())))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            Optional<DataType> dataType = ExtractDataType.parse(line);
                    if (dataType.isPresent())
                        switch (dataType.get()) {
                            case SALESMEN:
                                accumulateSalesmen(acummulator, line);
                                break;
                            case CLIENT:
                                accumulateClients(acummulator, line);
                                break;
                            case SALE:
                                accumulateSalesmenResult(acummulator, line);
                                accumulateSalesResult(acummulator, line);
                                break;
                        }
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            return acummulator;
        });
    }

    private void accumulateSalesResult(Acummulator acummulator, String line) {
        BigDecimal value = ExtractSaleValue.parse(line);
        Long id = ExtractSaleId.parse(line);
        if (acummulator.getBestSaleValue().compareTo(value) <= 0) {
            acummulator.updateBestSale(id, value);
        }
    }

    private void accumulateSalesmen(Acummulator acummulator, String line) {
        acummulator.getSalesmenCpf().add(ExtractSalesmanCpf.parse(line));
    }

    private void accumulateClients(Acummulator acummulator, String line) {
        acummulator.getClientsCnpj().add(ExtractClientCnpj.parse(line));
    }

    private void accumulateSalesmenResult(Acummulator acummulator, String line) {
        SalesmanResult salesmanResult = SalesmanResult.builder()
                .name(ExtractSalesmanNameFromSales.parse(line))
                .totalSale(ExtractSaleValue.parse(line))
                .build();

        if (acummulator.getSalesmenResults().contains(salesmanResult)) {
            acummulator.getSalesmenResults().stream()
                    .filter(sr -> sr.equals(salesmanResult))
                    .forEach(sr2 -> sr2.addSaleValue(salesmanResult.getTotalSale()));
        } else {
            acummulator.getSalesmenResults().add(salesmanResult);
        }
    }

    private List<Acummulator> getAccumulatorList(List<Future<Acummulator>> batchProcessed) {
        return batchProcessed.stream().map(res -> {
            try {
                return res.get();
            } catch (InterruptedException | ExecutionException e) {
                log.error(e.getMessage(), e);
                return null;
            }
        }).collect(Collectors.toList());
    }
}
