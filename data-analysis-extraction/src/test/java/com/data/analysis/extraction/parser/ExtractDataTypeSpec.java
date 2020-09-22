package com.data.analysis.extraction.parser;

import com.data.analysis.extraction.domain.enumeration.DataType;
import com.data.analysis.extraction.infra.parser.ExtractDataType;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ExtractDataTypeSpec {

    @Test
    public void when_parsingSalesMenType() {
        final String line = "001;1234567891235;Paulo;40000.99";

        Optional<DataType> dataType = ExtractDataType.parse(line);

        assertThat(dataType.get(), is(equalTo(DataType.SALESMEN)));
    }

    @Test
    public void when_parsingClientType() {
        final String line = "002;2345675434544345;Jose da Silva;Rural";

        Optional<DataType> dataType = ExtractDataType.parse(line);

        assertThat(dataType.get(), is(equalTo(DataType.CLIENT)));
    }

    @Test
    public void when_parsingSaleType() {
        final String line = "003;08;[1-34-10,2-33-1.50,3-40-0.10];Paulo";

        Optional<DataType> dataType = ExtractDataType.parse(line);

        assertThat(dataType.get(), is(equalTo(DataType.SALE)));
    }

    @Test
    public void when_parsingEmptyType() {
        final String line = "004;08;[1-34-10,2-33-1.50,3-40-0.10];Paulo";

        Optional<DataType> dataType = ExtractDataType.parse(line);

        assertTrue(dataType.isEmpty());
    }
}
