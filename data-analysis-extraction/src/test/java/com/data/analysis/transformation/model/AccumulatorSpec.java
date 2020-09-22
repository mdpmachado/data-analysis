package com.data.analysis.transformation.model;

import com.data.analysis.extraction.domain.model.Acummulator;
import com.data.analysis.extraction.domain.model.SalesmanResult;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class AccumulatorSpec {

    @Test
    public void when_updateBestSale() {
        Acummulator acummulator = Acummulator.builder().build();

        acummulator.updateBestSale(1l, BigDecimal.ONE);
        acummulator.updateBestSale(2l, BigDecimal.TEN);

        Assert.assertThat(acummulator.getBestSaleId(), is(equalTo(2L)));
    }
}
