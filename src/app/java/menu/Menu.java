package menu;


import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Sale.SaleService;
import customer.Customer;
import customer.CustomerType;

import product.Product;

import indicator.RegionType;
import utils.Resources;


public class Menu {
    private final List<Customer> customers;
    private final List<Product> products;
    private final SaleService saleService;

    private static final String CARTAO_FAKE = "8390291829384948";
    private static final String CARTAO_LOJA_FAKE = "4296131829384948";
    public Menu() {
        customers = new ArrayList<>();
        seedCustomers();
        products = new ArrayList<>();
        seedProducts();
        saleService = new SaleService();
    }

    private void seedCustomers() {
        Customer joao = new Customer("João", CustomerType.STANDARD, RegionType.DISTRITO_FEDERAL, true);
        joao.addSale(LocalDate.now().minusDays(10), 500.0);
        joao.addSale(LocalDate.now().minusDays(15), 200.0);
        customers.add(joao);

        Customer maria = new Customer("Maria", CustomerType.SPECIAL, RegionType.SUDESTE, false);
        maria.addSale(LocalDate.now().minusDays(20), 1200.0);
        customers.add(maria);

        Customer pedro = new Customer("Pedro", CustomerType.PRIME, RegionType.CENTRO_OESTE, true);
        pedro.addSale(LocalDate.now().minusDays(30), 800.0);
        pedro.addSale(LocalDate.now().minusDays(5), 400.0);
        customers.add(pedro);

        Customer Bruna = new Customer("Bruna", CustomerType.SPECIAL, RegionType.SUDESTE, true);
        Bruna.addSale(LocalDate.now().minusDays(30), 800.0);
        Bruna.addSale(LocalDate.now().minusDays(5), 400.0);
        customers.add(Bruna);
    }

    private void seedProducts() {
        products.add(new Product(1, "Bola de praia", 15.5, "unidade"));
        products.add(new Product(2, "Bastão de cola", 5, "unidade"));
        products.add(new Product(3, "Tecido azul", 9.99, "metro"));
    }

    public void start() {
        System.out.println("Bem-vindo ao sistema de gerenciamento de clientes e vendas!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Cadastrar produto");
            System.out.println("3. Realizar venda");
            System.out.println("4. Calcular vendas do último mês para cada cliente");
            System.out.println("5. Verificar status de cliente especial");
            System.out.println("6. Lista Clientes");
            System.out.println("7. Lista Produtos");
            System.out.println("8. Lista Vendas");
            System.out.println("0. Sair");

            int choice = Resources.readInt("Escolha uma opção");

            switch (choice) {
                case 1:
                    registerCustomer();
                    break;
                case 2:
                    registerProduct();
                    break;
                case 3:
                    makeSale();
                    break;
                case 4:
                    calculateSalesLastMonth();
                    break;
                case 5:
                    checkSpecialStatus();
                    break;
                case 6:
                    listCustomers();
                    break;
                case 7:
                    listProducts();
                    break;
                case 8:
                    listSales();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        }
    }

    public boolean hasProductWithCode(int code) {
        for (Product product : products) {
            if (product.getCode() == code) {
                return true;
            }
        }
        return false;
    }

    private void registerCustomer() {
        System.out.println("\nCadastro de Cliente:");

        String name = Resources.readString("Nome");
        int typeChoice = Resources.readInt("Tipo (1 - Padrão, 2 - Especial, 3 - Prime)");
        CustomerType type = switch (typeChoice) {
            case 1 -> CustomerType.STANDARD;
            case 2 -> CustomerType.SPECIAL;
            case 3 -> CustomerType.PRIME;
            default -> {
                System.out.println("Tipo inválido. Será cadastrado como padrão.");
                yield CustomerType.STANDARD;
            }
        };
        int regionChoice = Resources.readInt("Tipo (1 - Norte, 2 - Nordeste, 3 - Centro Oeste, 4 - Sudeste, 5 - SUL, 6 -> DISTRITO FEDERAL)");
        RegionType regionType = switch (regionChoice) {
            case 1 -> RegionType.NORTE;
            case 2 -> RegionType.NORDESTE;
            case 3 -> RegionType.CENTRO_OESTE;
            case 4 -> RegionType.SUDESTE;
            case 5 -> RegionType.SUL;
            case 6 -> RegionType.DISTRITO_FEDERAL;
            default -> {
                System.out.println("Tipo inválido. Será cadastrado como padrão.");
                yield RegionType.DISTRITO_FEDERAL; // Alterar
            }
        };
        boolean isCapital = Resources.readBoolean("É capital? (0 - Sim / 1 - Não)");

        Customer newCustomer = new Customer(name, type, regionType, isCapital);
        customers.add(newCustomer);

        System.out.println("Cliente cadastrado com sucesso!");
    }

    private void registerProduct() {
        System.out.println("\nCadastro de Produto:");

        int code = Resources.readInt("Código");
        while(hasProductWithCode(code)){
            System.out.println("\nCódigo já cadastrado.");
            code = Resources.readInt("Digite outro código");
        }
        String description = Resources.readString("Descrição");
        double price = Resources.readDouble("Preço");
        String unit = Resources.readString("Unidade");

        Product newProduct = new Product(code, description, price, unit);
        products.add(newProduct);

        System.out.println("Produto cadastrado com sucesso!");
    }

    private void calculateSalesLastMonth() {
        System.out.println("\nCalculando vendas do último mês para cada cliente:");
        for (Customer customer : customers) {
            double salesLastMonth = customer.getSalesLastMonth();
            System.out.println("Cliente: " + customer.getName() + " - Vendas do último mês: R$ " + salesLastMonth);
        }
    }

    private void checkSpecialStatus() {
        System.out.println("\nVerificando status de clientes especiais:");
        for (Customer customer : customers) {
            boolean isSpecialEligible = customer.isEligibleForSpecial();
            System.out.println("Cliente: " + customer.getName() + " - Elegível para ser especial: " + (isSpecialEligible ? "Sim" : "Não"));
        }
    }

    private void listCustomers() {
        System.out.println("\nLista de Clientes:");

        if (customers.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (int i = 0; i < customers.size(); i++) {
                Customer customer = customers.get(i);
                System.out.println("Cliente " + (i + 1) + ":");
                System.out.println("Nome: " + customer.getName());
                System.out.println("Tipo: " + customer.getType());
                System.out.println("Estado: " + customer.getState());
                System.out.println("Capital: " + (customer.isCapital() ? "Sim" : "Não"));
                System.out.println("------------------------------");
            }
        }
    }

    private void makeSale() {
        System.out.println("\nRealizando Venda:");

        ArrayList<Product> productList = selectProducts();

        if(productList.isEmpty()) {
            System.out.println("Nenhum Produto Selecionado! Retornando...");
            return;
        }

        int codCustomer = selectCustomer();
        if(codCustomer == 0) System.out.println("COMPRA CANCELADA!");
        Optional<Customer> customer = findCustomer(codCustomer);

        String paymentMethod = selectPaymentMethod();
        if(paymentMethod.equals("0")) return;

        System.out.println("\nVenda Adicionada com sucesso!");
    }

    private ArrayList selectProducts() {

        listProducts();

        int codProduct;
        ArrayList<Product> productList = new ArrayList<>();
        do {
            codProduct = Resources.readInt("Digite o codigo do produto (ou 0 para finalizar):");

            if(codProduct == 0) break;

            Optional<Product> product;
            if(hasProductWithCode(codProduct)) {
                product = findProduct(codProduct);
                productList.add(product.get());
                System.out.println("Produto adicionado ao carrinho!");
            } else {
                System.out.println("Codigo Invalido!");
            }

        } while (true);

        return productList;
    }

    private int selectCustomer() {
        listCustomers();

        int codCustomer;
        do {
            codCustomer = Resources.readInt("Digite o codigo do Cliente (0 para cancelar)");

            if(codCustomer == 0 || hasCustomer(codCustomer)) break;

            System.out.println("Cliente não encontrado! Tente novamente");

        } while (true);

        return codCustomer;
    }

    private String selectPaymentMethod() {
        String paymentMethod;
        do {
            paymentMethod = Resources.readString("DIGITE O NUMERO DO SEU CARTAO (ou 0 para cancelar a compra)");


            //apenas para facilitar na hora de inserir o numero do cartão
            if(paymentMethod.equals("100")) {
                return CARTAO_FAKE;
            } else if (paymentMethod.equals("99")) {
                return CARTAO_LOJA_FAKE;
            }

            if(paymentMethod.equals("0")) {
                System.out.println("COMPRA CANCELADA!");
                break;
            } else if(paymentMethod.length() != 16) {
                System.out.println("CARTAO INVALIDO, DEVE TER 16 DIGITOS!");
            }

        } while (paymentMethod.length() != 16);

        return paymentMethod;
    }

    private boolean hasCustomer(int codCustomer) {

        return codCustomer <= customers.size() && codCustomer > 0 && Optional.ofNullable(customers.get(codCustomer-1)).isPresent();
    }

    private void listProducts() {
        System.out.println("\nLista de Produtos:");

        if (products.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                System.out.println("Produto " + (i + 1) + ":");
                System.out.println("Codigo: " + product.getCode());
                System.out.println("Descrição: " + product.getDescription());
                System.out.println("Preço: " + product.getPrice());
                System.out.println("Unidade: " + product.getUnit());
                System.out.println("------------------------------");
            }
        }
    }

    private void listSales() {
        if(saleService.getSales().isEmpty()) System.out.println("Nenhuma Venda Realizada!");
        System.out.println("VENDAS REALIZADAS: ");
        saleService.getSales().forEach(sale -> System.out.println(sale.toString() + "-------------------------\n"));
    }

    private Optional<Product> findProduct(int code) {
       return products.stream().filter(f -> code == f.getCode()).toList().stream().findFirst();
    }

    private Optional <Customer> findCustomer(int code) {
        return Optional.ofNullable(customers.get(code-1));
    }
}