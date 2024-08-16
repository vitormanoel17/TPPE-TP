package Sale;

// A extração desta classe centraliza toda a lógica relacionada a pagamento,
// permitindo que mudanças nessa lógica afetem apenas esta classe, respeitando o princípio da responsabilidade única (SRP).
public class PaymentDetails {
    private final String paymentMethod;
    private final float amount;
    private float discount;
    private float tax;
    private float shipping;

    // Construtor simples que inicializa os detalhes de pagamento, facilitando a criação de objetos de pagamento.
    public PaymentDetails(String paymentMethod, float amount) {
        this.paymentMethod = paymentMethod;
        this.amount = amount;
    }

    // Métodos de acesso e modificação foram claramente separados,
    // permitindo que cada aspecto do pagamento seja tratado de forma independente.
    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public float getAmount() {
        return this.amount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getDiscount() {
        return this.discount;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public float getTax() {
        return this.tax;
    }

    public void setShipping(float shipping) {
        this.shipping = shipping;
    }

    public float getShipping() {
        return this.shipping;
    }
}
