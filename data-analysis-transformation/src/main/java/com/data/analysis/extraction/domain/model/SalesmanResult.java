package com.data.analysis.extraction.domain.model;


import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Getter
@EqualsAndHashCode(of = "name")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SalesmanResult implements Serializable {

    private String name;

    @Builder.Default
    private BigDecimal totalSale = BigDecimal.ZERO;

    public void addSaleValue(BigDecimal saleValue) {
        this.totalSale = this.totalSale.add(saleValue);
    }
}
