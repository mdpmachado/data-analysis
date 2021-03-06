package com.data.analysis.extraction.infra.parser;

import com.data.analysis.extraction.infra.constantes.Constantes;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class ExtractSaleValue {

    public static final String STR_REPLACING = "]";
    public static final String STR_REPLACED = "";

    public static BigDecimal parse(String line) {
        return Stream.of(line.split(Constantes.SEMICOLON)[2].split(Constantes.COMMA))
                .map(s -> new BigDecimal(s.split(Constantes.HYPHEN)[2].replace(STR_REPLACING, STR_REPLACED)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
