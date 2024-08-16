import Sale.*;
import customer.Customer;
import customer.CustomerType;
import indicator.RegionType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import product.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class ProcessSaleTest {

    Customer customerTest;
    ArrayList<Product> productsTest;
    String paymentMethodTest;
    float amountResult;
    SaleService saleService;

    public ProcessSaleTest(Customer customerTest, ArrayList<Product> productsTest, String paymentMethodTest, float amountResult) {
        this.customerTest = customerTest;
        this.productsTest = productsTest;
        this.paymentMethodTest = paymentMethodTest;
        this.amountResult = amountResult;
    }

    @Before
    public void setup() {
       saleService = new SaleService();
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][] {
                {
                    new Customer("Vitor", CustomerType.SPECIAL, RegionType.CENTRO_OESTE,false),
                    new ArrayList<>(Arrays.asList(
                            new Product(1, "Bola de praia", 15.5, "unidade"),
                            new Product(2, "Bastão de cola", 5, "unidade")
                        )
                    )
                    ,"19203930291029283", 30.50f
                },
                {
                    new Customer("Vitor", CustomerType.PRIME, RegionType.CENTRO_OESTE,false),
                    new ArrayList<>(Arrays.asList(
                            new Product(1, "Bola de praia", 20.5, "unidade"),
                            new Product(2, "Bastão de cola", 5, "unidade")
                        )
                    )
                    , "19203930291029283",29.58f
                },
                {
                    new Customer("Vitor", CustomerType.PRIME, RegionType.CENTRO_OESTE,false),
                    new ArrayList<>(Arrays.asList(
                            new Product(1, "Bola de praia", 22.5, "unidade"),
                            new Product(2, "Bastão de cola", 5, "unidade")
                    )
                    )
                    ,"19203930291029283",31.9f
                },
                {
                    new Customer("Vitor", CustomerType.STANDARD, RegionType.CENTRO_OESTE,false),
                    new ArrayList<>(Arrays.asList(
                            new Product(1, "Bola de praia", 22.5, "unidade"),
                            new Product(2, "Bastão de cola", 5, "unidade")
                    )
                    )
                    ,"19203930291029283",44.9f
                },
        };

    }

    @Test
    public void testProcessSale() {
        Assert.assertEquals(saleService.processSale(customerTest, productsTest, paymentMethodTest),  amountResult, 0.01);
    }

}
