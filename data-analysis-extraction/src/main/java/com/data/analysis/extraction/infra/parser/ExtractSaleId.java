package com.data.analysis.extraction.infra.parser;

import com.data.analysis.extraction.infra.constantes.Constantes;

public class ExtractSaleId {

    public static Long parse(String line) {
        return Long.valueOf(line.split(Constantes.SEMICOLON)[1]);
    }
}
