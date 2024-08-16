package customer;

import java.util.List;
import indicator.RegionType;
import sales.Sale;

import java.time.LocalDate;
import java.util.ArrayList;

public class Customer {
    private final String name;
    private final CustomerType type;
    private final RegionType state;
    private final boolean isCapital;
    private final List<Sale> sales;

    public Customer(String name, CustomerType type, RegionType state, boolean isCapital) {
        this.name = name;
        this.type = type;
        this.state = state;
        this.isCapital = isCapital;
        this.sales = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public CustomerType getType() {
        return this.type;
    }

    public RegionType getState() {
        return this.state;
    }

    public boolean isCapital() {
        return this.isCapital;
    }
    public void addSale(LocalDate date, double amount) {
        Sale sale = new Sale(amount, date);
        sales.add(sale);
    }
     public double getSalesLastMonth() {
        LocalDate now = LocalDate.now();
        LocalDate oneMonthAgo = now.minusMonths(1);
        return sales.stream()
                .filter(sale -> sale.getDate().isAfter(oneMonthAgo) && sale.getDate().isBefore(now))
                .mapToDouble(Sale::getAmount)
                .sum();
    }

    public boolean isEligibleForSpecial() {
        return getSalesLastMonth() > 1000.0; // Criterion de exemplar para ser especial
    }
}
