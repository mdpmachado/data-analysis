package com.data.analysis.extraction.service;

import com.data.analysis.extraction.domain.service.ExtractDatFileService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ExtractDatFileSpec {

    private ExtractDatFileService service;

    @Value("${app.data-directory.in}")
    private String inDirectory;

    @Before
    public void setUp() {
        service = Mockito.mock(ExtractDatFileService.class);
    }

    @Test
    public void when_accumulateSalesmen() throws Exception {
        service.execute();
    }
}
