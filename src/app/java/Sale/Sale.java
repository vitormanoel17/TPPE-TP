package Sale;

import customer.Customer;
import product.Product;

import java.util.ArrayList;
import java.util.Date;

public class Sale {
    // A extração da classe PaymentDetails permite que a lógica relacionada a pagamento seja encapsulada,
    // seguindo o princípio da responsabilidade única (SRP). Isso facilita a manutenção e a expansão futura.
    private Date date;
    private Customer customer;
    private ArrayList<Product> products;
    private PaymentDetails paymentDetails;

    // Este construtor permite a inicialização completa de uma venda com todos os detalhes necessários,
    // delegando a responsabilidade de manipulação dos detalhes de pagamento para PaymentDetails.
    public Sale(Date date, Customer customer, ArrayList<Product> products, PaymentDetails paymentDetails) {
        this.date = date;
        this.customer = customer;
        this.products = products;
        this.paymentDetails = paymentDetails;
    }

    // Este construtor alternativo foi mantido para compatibilidade, criando internamente a instância de PaymentDetails,
    // o que assegura a continuidade dos testes existentes sem modificações adicionais.
    public Sale(Date date, Customer customer, ArrayList<Product> products, String paymentMethod, float amount) {
        this.date = date;
        this.customer = customer;
        this.products = products;
        this.paymentDetails = new PaymentDetails(paymentMethod, amount);
    }

    // Métodos de acesso foram mantidos simples, agora delegando a responsabilidade de cálculos de pagamento para PaymentDetails,
    // o que reduz a complexidade da classe Sale.
    public Date getDate() {
        return this.date;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    public ArrayList<Product> getProduct() {
        return this.getProducts();
    }

    public String getPaymentMethod() {
        return this.paymentDetails.getPaymentMethod();
    }

    public float getAmount() {
        return this.paymentDetails.getAmount();
    }

    public void setDiscount(float discount) {
        this.paymentDetails.setDiscount(discount);
    }

    public void setTax(float tax) {
        this.paymentDetails.setTax(tax);
    }

    public void setShipping(float shipping) {
        this.paymentDetails.setShipping(shipping);
    }

    // Métodos adicionais foram introduzidos para expor detalhes de pagamento, mantendo a coesão na classe Sale.
    public float getDiscount() {
        return this.paymentDetails.getDiscount();
    }

    public float getTax() {
        return this.paymentDetails.getTax();
    }

    public float getShipping() {
        return this.paymentDetails.getShipping();
    }

    // Este metodo é um placeholder para manter compatibilidade, demonstrando como a refatoração pode ser feita
    // de forma a não quebrar funcionalidades existentes.
    public void setCashback(float cashback) {
    }

    // A lógica de formatação foi extraída para a classe SaleFormatter, melhorando a separação de responsabilidades
    // e facilitando a manutenção do código.
    @Override
    public String toString() {
        return SaleFormatter.format(this);
    }
}
