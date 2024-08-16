package product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ProductTest {
    private int testCode;
    private String testDescription;
    private double testPrice;
    private String testUnit;

    public ProductTest(int code, String description, double price, String unit) {
        this.testCode = code;
        this.testDescription = description;
        this.testPrice = price;
        this.testUnit = unit;
    }

    @Parameters(name = "{index}: Product({0}, {1}, {2}, {3})")
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][] {
            {1, "Bola de praia", 15.5, "unidade"},
            {2, "Bastão de cola", 5, "unidade"},
            {3, "Tecido azul", 9.99, "metro"},
            {4, "Corda trançada", 5, "metro"}
        });
    }

    @Test
    public void testProduct() {
        Product product = new Product(testCode, testDescription, testPrice, testUnit);

        assertEquals(testCode, product.getCode());
        assertEquals(testDescription, product.getDescription());
        assertEquals(testPrice, product.getPrice(), 0);
        assertEquals(testUnit, product.getUnit());
    }
}