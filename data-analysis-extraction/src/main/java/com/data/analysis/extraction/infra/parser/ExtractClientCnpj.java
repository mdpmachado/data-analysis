package com.data.analysis.extraction.infra.parser;

import com.data.analysis.extraction.infra.constantes.Constantes;

public class ExtractClientCnpj {

    public static String parse(String line) {
        return line.split(Constantes.SEMICOLON)[1];
    }
}
