package br.com.pedro.ebac;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
class DominioTest {
    @Test void rejeitaPrecoNegativo() {assertThrows(IllegalArgumentException.class,()->new Produto("X","Teste",new BigDecimal("-1.00"),"Teste"));}
    @Test void rejeitaQuantidadeZero() {
        Cliente c=new Cliente("00000000001","Exemplo","exemplo@example.invalid");
        Produto p=new Produto("X","Teste",BigDecimal.ONE,"Teste");
        assertThrows(IllegalArgumentException.class,()->new Venda(c).adicionar(p,0));
    }
}
