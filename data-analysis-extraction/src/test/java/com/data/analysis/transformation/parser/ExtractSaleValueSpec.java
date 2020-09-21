package com.data.analysis.transformation.parser;

import com.data.analysis.extraction.infra.parser.ExtractSaleValue;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ExtractSaleValueSpec {

    @Test
    public void when_parsingSaleValue() {

        final String line = "003;08;[1-34-10,2-33-1.50,3-40-0.10];Paulo";

        BigDecimal value = ExtractSaleValue.parse(line);

        assertThat(value, is(equalTo(new BigDecimal("11.60"))));
    }
}
