package com.data.analysis.transformation.domain.service;

import com.data.analysis.transformation.domain.model.Acummulator;
import com.data.analysis.transformation.domain.model.IndicatorResult;
import com.data.analysis.transformation.domain.model.SalesmanResult;
import com.data.analysis.transformation.infra.kafka.AbstractProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class TransformDatFileService extends AbstractProducer {

    private final MessageSendingService messageSendingService;

    public TransformDatFileService(MessageSendingService messageSendingService) {
        this.messageSendingService = messageSendingService;
    }

    public void execute(List<Acummulator> batchInProcess) throws Exception {
        log.info("Transform data.");
        Set<String> salesmenCpf = new HashSet<>();
        Set<String> clientsCnpj = new HashSet<>();
        Long bestSaleId = null;
        BigDecimal bestSaleValue = BigDecimal.ZERO;
        Set<SalesmanResult> salesmanResults = new HashSet<>();

        for (Acummulator acummulator : batchInProcess) {
            salesmenCpf.addAll(acummulator.getSalesmenCpf());
            clientsCnpj.addAll(acummulator.getClientsCnpj());

            if (acummulator.getBestSaleValue().compareTo(bestSaleValue) > 0) {
                bestSaleId = acummulator.getBestSaleId();
                bestSaleValue = acummulator.getBestSaleValue();
            }

            for (SalesmanResult salesmanResult : acummulator.getSalesmenResults()) {
                if (salesmanResults.contains(salesmanResult)) {
                    salesmanResults.stream()
                            .filter(sr -> sr.equals(salesmanResult))
                            .forEach(sr2 -> sr2.addSaleValue(salesmanResult.getTotalSale()));
                } else {
                    salesmanResults.add(salesmanResult);
                }
            }
        }

        IndicatorResult indicatorResult = IndicatorResult.builder()
                .countClients(clientsCnpj.size())
                .countSalesmen(salesmenCpf.size())
                .bestSale(bestSaleId)
                .worseSalesman(salesmanResults.stream().min(Comparator.comparing(SalesmanResult::getTotalSale)).get().getName())
                .build();

        sendIndicatorToReport(indicatorResult);
    }

    private void sendIndicatorToReport(IndicatorResult indicatorResult) throws Exception {
        messageSendingService.sendIndicatorToReport(indicatorResult);
    }
}
