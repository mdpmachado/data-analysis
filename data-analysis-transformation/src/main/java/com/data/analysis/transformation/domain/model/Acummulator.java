package com.data.analysis.transformation.domain.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Acummulator implements Serializable {
    
    @Builder.Default
    private Set<String> clientsCnpj = new HashSet<>();

    @Builder.Default
    private Set<String> salesmenCpf = new HashSet<>();

    @Builder.Default
    private Set<SalesmanResult> salesmenResults = new HashSet<>();

    @Builder.Default
    private BigDecimal bestSaleValue = BigDecimal.ZERO;

    private Long bestSaleId;

    public void updateBestSale(Long id, BigDecimal value) {
        bestSaleId = id;
        bestSaleValue = value;
    }
}
