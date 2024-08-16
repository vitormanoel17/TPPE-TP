package Sale;

import customer.Customer;
import product.Product;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class SaleProcessor {

    private Customer customer;
    private ArrayList<Product> products;
    private String paymentMethod;
    private SaleService saleService;

    public SaleProcessor(Customer customer, ArrayList<Product> products, String paymentMethod, SaleService saleService) {
        this.customer = customer;
        this.products = products;
        this.paymentMethod = paymentMethod;
        this.saleService = saleService;
    }

    public float processSale() {
        float amount = 0;

        for(Product p : this.products){
            amount+= p.getPrice();
        }

        float discount = saleService.calculateDiscount(this.customer.getType(), amount, this.paymentMethod);
        amount -= discount;
        float tax  = saleService.calculateTax(this.customer.getState(), amount);
        float shipping = saleService.calculateShipping(this.customer.getState(), this.customer.isCapital(), this.customer.getType());
        amount += tax + shipping;

        float cashback = saleService.calculateCashback(this.customer, amount, this.paymentMethod);

        Sale sale = new Sale(Date.from(Instant.now()), this.customer, this.products, this.paymentMethod, amount);
        sale.setDiscount(discount);
        sale.setTax(tax);
        sale.setShipping(shipping);
        sale.setCashback(cashback);

        saleService.addSale(sale);

        System.out.println(sale);
        if (cashback > 0) {
            System.out.printf("Cashback recebido: R$ %.2f%n", cashback);
        }

        return amount;
    }
}
