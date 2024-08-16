package Sale;

import product.Product;

// A extração de SaleFormatter permite isolar a lógica de formatação,
// seguindo o princípio da responsabilidade única (SRP), o que facilita a personalização e manutenção da formatação.
public class SaleFormatter {

    // Este metodo formatador permite que a lógica de formatação seja separada da classe Sale,
    // aumentando a coesão e permitindo que Sale se concentre em gerenciar dados de vendas.
    public static String format(Sale sale) {
        // Concatena os produtos da venda em uma string formatada.
        String products = sale.getProduct().stream()
                .map(Product::toString)
                .reduce("", String::concat);

        // Formata os detalhes da venda, incluindo produtos, valor total, impostos, desconto e frete.
        return "DATE: " + sale.getDate() + " | " +
                "CLIENTE: " + sale.getCustomer().getName() + "-" + sale.getCustomer().getType().name() + "-" + sale.getCustomer().getState().name() + " | " +
                "\nPRODUTOS: \n" + products + " | " +
                "VALOR TOTAL: " + sale.getAmount() + "\n" +
                "Imposto: " + sale.getTax() + "\n" +
                "Desconto: " + sale.getDiscount() + "\n" +
                "Frete: " + sale.getShipping();
    }
}
