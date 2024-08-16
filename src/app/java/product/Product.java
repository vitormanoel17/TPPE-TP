package product;

public class Product {
    public int code;
    public String description;
    public double price;
    public String unit;

    public Product(int code, String description, double price, String unit) {
        this.code = code;
        this.description = description;
        this.price = price;
        this.unit = unit;
    }

    public int getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public double getPrice() {
        return this.price;
    }

    public String getUnit() {
        return this.unit;
    }

    @Override
    public String toString() {
        return "Nome: " + this.getDescription() + " | " +
                "Valor: " + this.price + "\n";
    }

}