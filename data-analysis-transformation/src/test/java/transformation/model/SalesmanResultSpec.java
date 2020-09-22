package transformation.model;

import com.data.analysis.extraction.domain.model.SalesmanResult;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class SalesmanResultSpec {

    @Test
    public void when_addSaleValue() {
        SalesmanResult salesManResult = SalesmanResult.builder().build();

        salesManResult.addSaleValue(BigDecimal.TEN);
        salesManResult.addSaleValue(BigDecimal.TEN);
        salesManResult.addSaleValue(BigDecimal.ONE);
        salesManResult.addSaleValue(BigDecimal.ONE);
        salesManResult.addSaleValue(BigDecimal.TEN);
        salesManResult.addSaleValue(BigDecimal.TEN);

        Assert.assertThat(salesManResult.getTotalSale(), is(equalTo(BigDecimal.valueOf(42L))));
    }
}
