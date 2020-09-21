package com.data.analysis.extraction.infra.parser;

import com.data.analysis.extraction.infra.constantes.Constantes;
import com.data.analysis.extraction.domain.enumeration.DataType;

import java.util.Optional;

public final class ExtractDataType {

    public static Optional<DataType> parse(String text) {
        String code = text.split(Constantes.SEMICOLON)[0];
        return DataType.fromCode(code);
    }
}
