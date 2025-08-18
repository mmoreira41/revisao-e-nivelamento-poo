import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProdutoPerecivel extends Produto {
    
    private double Desconto = 0.25;
    private int PRAZO_DESCONTO = 7;
    private LocalDate dataValidade;

    public ProdutoPerecivel(String desc, double precoCusto, double margemLucro, LocalDate validade){
        super(desc, precoCusto, margemLucro);

        if(dataValidade.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Validade Anterior ao dia de hoje!");
        }
        dataValidade = validade;
    }

    public double valorDeVenda(){
        double desconto = 0d;

        int diaValidade = LocalDate.now().until(dataValidade).getDays();
        if(diaValidade<=PRAZO_DESCONTO){
            desconto = Desconto;
        }
        return(precoCusto * (1+margemLucro)) * (1-desconto);
    }


    public String toString(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");

        String dados = super.toString();
        dados +="\nValido até:" + formato.format(dataValidade);
        return dados;
    }

}
