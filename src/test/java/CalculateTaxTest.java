import Sale.SaleService;
import indicator.RegionType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CalculateTaxTest {

    SaleService saleService;
    RegionType regionTypeTest;
    float amountTest;
    float taxResult;
    public CalculateTaxTest(RegionType regionTypeTest, float amountTest, float taxResult) {
        this.regionTypeTest = regionTypeTest;
        this.amountTest = amountTest;
        this.taxResult = taxResult;
    }
    @Before
    public void setup () {
        saleService = new SaleService();
    }

    @Parameters
    public static Object[][] getParameters() {
        return new Object[][] {
                { RegionType.NORTE, 5000f, 800f},
                { RegionType.DISTRITO_FEDERAL, 10000f, 1800f},
                { RegionType.SUDESTE, 10000, 1600f },
                { RegionType.SUL, 10000, 1600f }
        };
    }

    @Test
    public void testCalculateTax() {
        Assert.assertEquals(saleService.calculateTax(regionTypeTest, amountTest), taxResult, 0.01);
    }
}
