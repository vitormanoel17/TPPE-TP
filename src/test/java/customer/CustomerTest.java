package customer;

import indicator.RegionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;




import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CustomerTest {

    private String testName;
    private CustomerType testType;
    private RegionType testState;
    private boolean testIsCapital;

    public CustomerTest(String testName, CustomerType testType, RegionType testState, boolean testIsCapital) {
        this.testName = testName;
        this.testType = testType;
        this.testState = testState;
        this.testIsCapital = testIsCapital;
    }

    @Parameters(name = "{index}: Customer({0}, {1}, {2}, {3})")
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][] {
                {"Joao", CustomerType.STANDARD, RegionType.SUL, true},
                {"Maria", CustomerType.STANDARD, RegionType.NORTE, true},
                {"Carlos", CustomerType.SPECIAL, RegionType.SUDESTE, true},
                {"Jessica", CustomerType.PRIME, RegionType.CENTRO_OESTE, true},
                {"Pedro", CustomerType.STANDARD, RegionType.NORDESTE, true},
                {"Leandro", CustomerType.STANDARD, RegionType.DISTRITO_FEDERAL, true},
                {"Jao", CustomerType.STANDARD, RegionType.SUL, true},
                {"Joao", CustomerType.STANDARD, RegionType.NORDESTE, false}
        });
    }

    @Test
    public void testCustomer() {
        Customer customer = new Customer(testName, testType, testState, testIsCapital);

        assertEquals(testName, customer.getName());
        assertEquals(testType, customer.getType());
        assertEquals(testState, customer.getState());
        assertEquals(testIsCapital, customer.isCapital());
    }
}
