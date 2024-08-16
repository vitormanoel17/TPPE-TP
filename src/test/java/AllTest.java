import customer.CustomerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        AddSaleTest.class,
        CalculateTaxTest.class,
        CalculateDiscountTest.class,
        CalculateCashBackTest.class,
        CalculateShippingTest.class,
        CustomerSpecialStatusTest.class,
        CustomerTest.class,
        SalesCalculationParameterizedTest.class,
        SaleTest.class,
        ProcessSaleTest.class
})
public class AllTest {

}
