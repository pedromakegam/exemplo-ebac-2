package br.com.pedro.ebac;

import org.junit.jupiter.api.Test;
import br.com.rpires.domain.Venda;
import br.com.rpires.domain.Produto;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
class VendaValidacaoTest {
    private Produto produto() {
        Produto p = new Produto(); p.setCodigo("EXEMPLO"); p.setNome("Produto de teste"); p.setValor(new BigDecimal("12.50")); return p;
    }
    @Test void rejeitaQuantidadeZeroNegativaENula() {
        Venda venda = new Venda();
        assertThrows(IllegalArgumentException.class, () -> venda.adicionarProduto(produto(),0));
        assertThrows(IllegalArgumentException.class, () -> venda.adicionarProduto(produto(),-1));
        assertThrows(IllegalArgumentException.class, () -> venda.adicionarProduto(produto(),null));
        assertEquals(BigDecimal.ZERO, venda.getValorTotal());
    }
    @Test void naoAlteraVendaCancelada() {
        Venda venda = new Venda(); venda.adicionarProduto(produto(),2); venda.setStatus(Venda.Status.CANCELADA);
        assertThrows(UnsupportedOperationException.class, () -> venda.adicionarProduto(produto(),1));
        assertThrows(UnsupportedOperationException.class, venda::removerTodosProdutos);
        assertEquals(new BigDecimal("25.00"), venda.getValorTotal());
    }
    @Test void recalculaTotalAoSomarERemoverItens() {
        Venda venda = new Venda(); venda.adicionarProduto(produto(),2); venda.adicionarProduto(produto(),1);
        assertEquals(new BigDecimal("37.50"), venda.getValorTotal());
        venda.removerProduto(produto(),1); assertEquals(new BigDecimal("25.00"), venda.getValorTotal());
    }
}
