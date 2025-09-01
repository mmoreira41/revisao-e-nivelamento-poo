import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pedido {

    /** Quantidade máxima de produtos de um pedido */
    private static final int MAX_PRODUTOS = 10;
    
    /** Porcentagem de desconto para pagamentos à vista */
    private static final double DESCONTO_PG_A_VISTA = 0.15;
    
    /** Vetor para armazenar os produtos do pedido */
    private Produto[] produtos;
    
    /** Data de criação do pedido */
    private LocalDate dataPedido;
    
    /** Indica a quantidade total de produtos no pedido até o momento */
    private int quantProdutos = 0;
    
    /** Indica a forma de pagamento do pedido sendo: 1, pagamento à vista; 2, pagamento parcelado */
    private int formaDePagamento;
    
    /** Construtor do pedido.
     *  Deve criar o vetor de produtos do pedido, 
     *  armazenar a data e a forma de pagamento informadas para o pedido. 
     */  
    public Pedido(LocalDate dataPedido, int formaDePagamento) {
        this.produtos = new Produto[MAX_PRODUTOS];
        this.dataPedido = dataPedido;
        this.formaDePagamento = formaDePagamento;
        this.quantProdutos = 0;
    }
    
    /**
     * Inclui um produto neste pedido e aumenta a quantidade de produtos armazenados no pedido até o momento.
     * @param novo O produto a ser incluído no pedido
     * @return true/false indicando se a inclusão do produto no pedido foi realizada com sucesso.
     */
    public boolean incluirProduto(Produto novo) {
        if (novo == null || quantProdutos >= MAX_PRODUTOS) {
            return false;
        }
        
        produtos[quantProdutos] = novo;
        quantProdutos++;
        return true;
    }
    
    /**
     * Calcula e retorna o valor final do pedido (soma do valor de venda de todos os produtos do pedido).
     * Caso a forma de pagamento do pedido seja à vista, aplica o desconto correspondente.
     * @return Valor final do pedido (double)
     */
    public double valorFinal() {
        double valorTotal = 0.0;
        
        for (int i = 0; i < quantProdutos; i++) {
            valorTotal += produtos[i].getPrecoDeVenda();
        }
        
        if (formaDePagamento == 1) {
            valorTotal = valorTotal * (1 - DESCONTO_PG_A_VISTA);
        }
        
        return valorTotal;
    }
    
    /**
     * Representação, em String, do pedido.
     * Contém um cabeçalho com sua data e o número de produtos no pedido.
     * Depois, em cada linha, a descrição de cada produto do pedido.
     * Ao final, mostra a forma de pagamento, o percentual de desconto (se for o caso) e o valor a ser pago pelo pedido.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        sb.append("Data do pedido: ").append(dataPedido.format(formatoData)).append("\n");
        sb.append("Pedido com ").append(quantProdutos).append(" produto");
        if (quantProdutos != 1) sb.append("s");
        sb.append(".\n");
        
        sb.append("Produtos no pedido:\n");
        for (int i = 0; i < quantProdutos; i++) {
            sb.append(produtos[i].toString()).append("\n");
        }
        
        if (formaDePagamento == 1) {
            sb.append("Pedido pago à vista. Percentual de desconto: 15,00%\n");
        } else {
            sb.append("Pedido pago parcelado.\n");
        }
        
        sb.append("Valor total do pedido: R$ ").append(String.format("%.2f", valorFinal()));
        
        return sb.toString();
    }
    
    /**
     * Igualdade de pedidos: caso possuam a mesma data. 
     * @param obj Outro pedido a ser comparado 
     * @return booleano true/false conforme o parâmetro possua a data igual ou não a este pedido.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Pedido pedido = (Pedido) obj;
        return dataPedido.equals(pedido.dataPedido);
    }
    
    // Getter necessário para a tarefa 2
    public LocalDate getDataPedido() {
        return dataPedido;
    }
}