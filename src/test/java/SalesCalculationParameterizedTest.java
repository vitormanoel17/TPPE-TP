import customer.Customer;
import customer.CustomerType;
import indicator.RegionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SalesCalculationParameterizedTest {

    private final String customerName;
    private final double expectedSales;

    
    public SalesCalculationParameterizedTest(String customerName, double expectedSales) {
        this.customerName = customerName;
        this.expectedSales = expectedSales;
    }

    @Test
    public void testCalculateSalesLastMonth() {
        Customer customer = createCustomerByName(customerName);
        double actualSales = customer.getSalesLastMonth();
        assertEquals(expectedSales, actualSales, 0.01); 
    }

    private Customer createCustomerByName(String name) {
        CustomerType type = CustomerType.STANDARD; 
        RegionType state = RegionType.DISTRITO_FEDERAL;
        boolean isCapital = false; 
    
        Customer customer = new Customer(name, type, state, isCapital);
    
        LocalDate now = LocalDate.now();
        if (name.equals("João")) {
            customer.addSale(now.minusMonths(1).plusDays(5), 300.0); 
            customer.addSale(now.minusMonths(1).plusDays(15), 500.0); 
        } else if (name.equals("Maria")) {
            customer.addSale(now.minusMonths(1).plusDays(3), 400.0); 
            customer.addSale(now.minusMonths(1).plusDays(10), 800.0); 
        } else if (name.equals("Pedro")) {
            customer.addSale(now.minusMonths(1).plusDays(8), 600.0); 
            customer.addSale(now.minusMonths(1).plusDays(20), 900.0); 
        }
            return customer;
    }
    

    @Parameters(name = "{index}: Cliente {0}, Vendas do Último Mês: {1}")
    public static Collection<Object[]> data() {
        return List.of(
                new Object[] { "João", 800.0 }, 
                new Object[] { "Maria", 1200.0 },
                new Object[] { "Pedro", 1500.0 }
        );
    }
}
