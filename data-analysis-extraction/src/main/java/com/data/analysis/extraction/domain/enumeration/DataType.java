package com.data.analysis.extraction.domain.enumeration;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum DataType {

    SALESMEN("001"), CLIENT("002"), SALE("003");

    private String code;

    public static Optional<DataType> fromCode(String code) {
        return Stream.of(values()).filter(dataType -> dataType.code.equals(code)).findFirst();
    }
}
