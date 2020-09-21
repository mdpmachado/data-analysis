package com.data.analysis.report.domain.model;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IndicatorResult {

    private Integer countSalesmen;
    private Integer countClients;
    private Long bestSale;
    private String worseSalesman;

}
