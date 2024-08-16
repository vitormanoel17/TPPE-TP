import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import indicator.RegionType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import customer.Customer;
import customer.CustomerType;

@RunWith(Parameterized.class)
public class CustomerSpecialStatusTest {
    private Customer customer;
    private double salesAmount;
    private boolean expectedEligibility;

    public CustomerSpecialStatusTest(double salesAmount, boolean expectedEligibility) {
        this.salesAmount = salesAmount;
        this.expectedEligibility = expectedEligibility;
    }

    @Before
    public void setUp() {
        customer = new Customer("Test Customer", CustomerType.STANDARD, RegionType.NORTE, false);
        LocalDate saleDate = LocalDate.now().minusDays(10); // Exemplo de data para a venda
        customer.addSale(saleDate, salesAmount);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            { 500.0, false },    
            { 1000.0, false },   
            { 1500.0, true },    
            { 2000.0, true }    
        });
    }

    @Test
    public void testIsEligibleForSpecial() {
        assertEquals(expectedEligibility, customer.isEligibleForSpecial());
    }
}

