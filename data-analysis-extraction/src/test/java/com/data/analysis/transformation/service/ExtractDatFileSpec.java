package com.data.analysis.transformation.service;

import com.data.analysis.extraction.domain.service.ExtractDatFileService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@TestPropertySource("classpath:application.yml")
public class ExtractDatFileSpec {

    @Autowired
    private ExtractDatFileService service;

    @Value("${app.data-directory.in}")
    private String inDirectory;

    @Before
    public void setUp() {
        service = Mockito.mock(ExtractDatFileService.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void when_accumulateSalesmen() throws IOException {
        final String line = "001;1234567891235;Paulo;40000.99";
        service.execute();

//        assertThat(dataType.get(), is(equalTo(DataType.SALESMEN)));
    }
}
