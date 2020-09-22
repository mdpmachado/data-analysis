package com.data.analysis.extraction.domain.service;

import com.data.analysis.extraction.domain.model.Acummulator;
import com.data.analysis.extraction.domain.model.IndicatorResult;
import com.data.analysis.extraction.domain.model.SalesmanResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class TransformDatFileService {

    public IndicatorResult execute(List<Acummulator> batchInProcess) {
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

        return IndicatorResult.builder()
                .countClients(clientsCnpj.size())
                .countSalesmen(salesmenCpf.size())
                .bestSale(bestSaleId)
                .worseSalesman(salesmanResults.stream().min(Comparator.comparing(SalesmanResult::getTotalSale)).get().getName())
                .build();
    }
}
