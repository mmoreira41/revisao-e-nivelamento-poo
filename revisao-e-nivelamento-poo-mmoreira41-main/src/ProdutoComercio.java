// SUBSTITUA APENAS OS MÉTODOS TODO NO SEU App.java PELOS CÓDIGOS ABAIXO:

/**
 * Lê os dados de um arquivo-texto e retorna um vetor de pedidos. Arquivo-texto no formato
 * N  (quantidade de pedidos) 
 * dataDoPedido;formaDePagamento;descrições dos produtos do pedido 
 * Deve haver uma linha para cada um dos pedidos. Retorna um vetor vazio em caso de problemas com o arquivo.
 * @param nomeArquivoDados Nome do arquivo de dados a ser aberto.
 * @return Um vetor com os pedidos carregados, ou vazio em caso de problemas de leitura.
 */
static Pedido[] lerPedidos(String nomeArquivoDados) {
    Scanner arquivo = null;
    int numPedidos, i = 0;
    String linha;
    Pedido[] pedidosCadastrados = new Pedido[MAX_PEDIDOS];
    
    try {
        arquivo = new Scanner(new File(nomeArquivoDados), Charset.forName("UTF-8"));
        
        numPedidos = Integer.parseInt(arquivo.nextLine());
        
        while (arquivo.hasNextLine() && i < MAX_PEDIDOS) {
            linha = arquivo.nextLine();
            String[] campos = linha.split(";");
            
            if (campos.length >= 3) {
                DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate data = LocalDate.parse(campos[0], formatoData);
                int formaPagamento = Integer.parseInt(campos[1]);
                
                Pedido pedido = new Pedido(data, formaPagamento);
                
                // Adicionar produtos ao pedido
                for (int j = 2; j < campos.length; j++) {
                    String nomeProduto = campos[j];
                    // Buscar produto no vetor de produtos cadastrados
                    for (int k = 0; k < quantosProdutos; k++) {
                        if (produtosCadastrados[k].getDescricao().equals(nomeProduto)) {
                            pedido.incluirProduto(produtosCadastrados[k]);
                            break;
                        }
                    }
                }
                
                pedidosCadastrados[i] = pedido;
                i++;
            }
        }
        quantPedidos = i;
        
    } catch (Exception e) {
        System.out.println("Erro ao ler arquivo de pedidos");
        quantPedidos = 0;
    } finally {
        if (arquivo != null) {
            arquivo.close();
        }
    }
    
    return pedidosCadastrados;
}

/** Localiza pedidos no vetor de pedidos cadastrados, a partir da data do pedido informada pelo usuário,
 *  e imprime seus dados.
 *  Em caso de não encontrar nenhum pedido, imprime uma mensagem padrão */
static void localizarPedidosPorData() {
    cabecalho();
    System.out.println("Localização de pedidos por data");
    
    if (quantPedidos == 0) {
        System.out.println("Nenhum pedido cadastrado.");
        return;
    }
    
    System.out.print("Digite a data (dd/mm/yyyy): ");
    String dataBusca = teclado.nextLine();
    
    try {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataParaBusca = LocalDate.parse(dataBusca, formatoData);
        
        boolean encontrou = false;
        
        for (int i = 0; i < quantPedidos; i++) {
            if (pedidosCadastrados[i].getDataPedido().equals(dataParaBusca)) {
                System.out.println(pedidosCadastrados[i].toString());
                System.out.println("---");
                encontrou = true;
            }
        }
        
        if (!encontrou) {
            System.out.println("Nenhum pedido encontrado para a data informada.");
        }
        
    } catch (Exception e) {
        System.out.println("Data em formato inválido.");
    }
}