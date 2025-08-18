import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Locale;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ProdutoPerecivelTest {

    static Produto produto;
        
    
    @BeforeAll
    static public void prepare(){
        produto = new ProdutoPerecivel("Produto teste", 100, 0.1,LocalDate.now().plusDays(10));
    }
    
    @Test
    public void calcularPrecoSemDescontoCorretamente(){
        assertEquals(110.0, produto.valorDeVenda(), 0.01);
    }


    @Test
    public void calcularPrecoComDescontoCorretamente(){
        produto = new ProdutoPerecivel("Produto teste", 1200, 0.1, LocalDate.now().plusDays(10));
        assertEquals(110.0 * 0.75, produto.valorDeVenda(), 0.01);
    }

    @Test
    public void naoCriarProdutoForaValidade(){
        assertThrows(IllegalArgumentException.class, () -> new ProdutoPerecivel("Produto teste", 1200, 0.1, LocalDate.now().plusDays(2)));
    }

}
