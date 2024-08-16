package sales;

import java.time.LocalDate;

public class Sale {
    private final double amount;
    private final LocalDate date;

    public Sale(double amount, LocalDate date) {
        this.amount = amount;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }
}
