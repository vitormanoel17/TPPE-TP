import Sale.Sale;
import customer.Customer;
import customer.CustomerType;
import indicator.RegionType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import product.Product;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@RunWith(Parameterized.class)
public class SaleTest {

    private Date testDate;
    private Customer testCustomer;
    private ArrayList<Product> testProduct;
    private String testPaymentMethod;

    private float amountTest;


    public SaleTest(Date testDate, Customer testCustomer, ArrayList<Product> testProduct, String testPaymentMethod, float amountTest) {
        this.testDate = testDate;
        this.testCustomer = testCustomer;
        this.testProduct = testProduct;
        this.testPaymentMethod = testPaymentMethod;
        this.amountTest = amountTest;
    }

    @Parameters(name = "{index}: Customer({0}, {1}, {2}, {3})")
    public static Collection<Object[]> parameters() {

        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, "Bicicleta",300,"unidade"));
        products.add(new Product(2,"Camisa",50,"unidade"));
        Customer customer1 = new Customer("Juliana", CustomerType.STANDARD, RegionType.CENTRO_OESTE,false);
        Customer customer2 = new Customer("Maria", CustomerType.STANDARD, RegionType.NORDESTE, true);
        Customer customer3 = new Customer("Jessica", CustomerType.PRIME, RegionType.SUL, true);
        Customer customer4 = new Customer("Leandro", CustomerType.STANDARD, RegionType.DISTRITO_FEDERAL, true);
        Customer customer5 = new Customer("Julia", CustomerType.STANDARD, RegionType.NORTE,false);

        return Arrays.asList(new Object[][] {
                {Date.from(Instant.parse("2010-12-07T10:15:30.00Z")), customer1, products, "0234567890123456", 350},
                {Date.from(Instant.parse("2014-06-11T10:15:30.00Z")), customer2, products, "2234567890123456", 350},
                {Date.from(Instant.parse("2023-11-13T10:15:30.00Z")), customer3, products, "3234567890123456", 350},
                {Date.from(Instant.parse("2024-02-25T10:15:30.00Z")), customer4, products, "41234567890123456", 350},
                {Date.from(Instant.parse("2020-01-14T10:15:30.00Z")), customer5, products, "5234567890123456", 350},
        });
    }
    @Test
    public void testSale() {
        Sale sale = new Sale(testDate, testCustomer,testProduct,testPaymentMethod, amountTest);

        Assert.assertEquals(sale.getDate(), testDate);
        Assert.assertEquals(sale.getCustomer(), testCustomer);
        Assert.assertEquals(sale.getProduct(), testProduct);
        Assert.assertEquals(sale.getPaymentMethod(), testPaymentMethod);
        Assert.assertEquals(sale.getAmount(), amountTest, .01);
    }




}
