import Sale.SaleService;
import customer.CustomerType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CalculateDiscountTest {

    SaleService saleService;
    CustomerType customerTypeTest;
    float amountTest;
    String paymentCardTest;
    float discountResult;


    public CalculateDiscountTest(CustomerType customerTypeTest, float amountTest, String paymentCardTest, float discountResult) {
        this.customerTypeTest = customerTypeTest;
        this.amountTest = amountTest;
        this.paymentCardTest = paymentCardTest;
        this.discountResult = discountResult;
    }
    @Before
    public void setup() {
        saleService = new SaleService();
    }

    @Parameters
    public static Object[][] getParameters() {
        return new Object[][] {
                {CustomerType.SPECIAL,3000f, "429611XXXXXXXXXX", 300f},
                {CustomerType.STANDARD,3000f, "429612XXXXXXXXXX", 0},
                {CustomerType.PRIME,3000f, "529613XXXXXXXXXX", 0},
                {CustomerType.PRIME,3000f, "429613XXXXXXXXXX", 300f},
                {CustomerType.SPECIAL,3000f, "429613XXXXXXXXXX", 600f},
        };
    }
    @Test
    public void testCalculateDiscount() {
        Assert.assertEquals(saleService.calculateDiscount(customerTypeTest,amountTest, paymentCardTest), discountResult, 0.01);
    }



}
