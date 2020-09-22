package transformation.service;

import com.data.analysis.transformation.domain.model.Acummulator;
import com.data.analysis.transformation.domain.service.TransformDatFileService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class TransformDatFileServiceSpec {

    private TransformDatFileService transformDatFileService;

    @Before
    public void setUp() {
        transformDatFileService = Mockito.mock(TransformDatFileService.class);
    }

    @Test
    public void when_accumulatorListNotNull() throws Exception {

        Acummulator acummulator = Acummulator.builder().bestSaleId(1L).build();

        List<Acummulator> acummulatorList = Arrays.asList(acummulator);

        Assert.assertNotNull(acummulatorList);
        transformDatFileService.execute(acummulatorList);
    }
}
