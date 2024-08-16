import Sale.SaleService;
import customer.Customer;
import customer.CustomerType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CalculateCashBackTest {

    SaleService saleService;
    Customer customerTest;
    float amountTest;
    String paymentCardTest;
    float cashBackResult;

    public CalculateCashBackTest(Customer customerTest, float amountTest, String paymentCardTest, float cashBackResult) {
        this.customerTest = customerTest;
        this.amountTest = amountTest;
        this.paymentCardTest = paymentCardTest;
        this.cashBackResult = cashBackResult;
    }

    @Before
    public void setup() {
        saleService = new SaleService();
    }

    @Parameters
    public static Object[][] getParameters() {
        return new Object[][] {
                {new Customer("Pedro", CustomerType.PRIME, null, true), 1000f, "429613XXXXXXXXXX", 50f},
                {new Customer("Pedro", CustomerType.PRIME, null, true), 1000f, "529613XXXXXXXXXX", 30f},
                {new Customer("João", CustomerType.STANDARD, null, true), 1000f, "429613XXXXXXXXXX", 0},
                {new Customer("Maria", CustomerType.SPECIAL, null, true), 1000f, "429613XXXXXXXXXX", 0},
                {new Customer("Pedro", CustomerType.PRIME, null, true), 500f, "429613XXXXXXXXXX", 25f},
                {new Customer("Pedro", CustomerType.PRIME, null, true), 500f, "529613XXXXXXXXXX", 15f},
        };
    }

    @Test
    public void testCalculateCashBack() {
        Assert.assertEquals(saleService.calculateCashback(customerTest, amountTest, paymentCardTest), cashBackResult, 0.01);
    }
}
