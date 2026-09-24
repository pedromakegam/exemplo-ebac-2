package br.com.pedro.ebac;

import org.junit.jupiter.api.Test;
import com.google.gson.JsonSyntaxException;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
class ProdutoJsonTest {
    @Test void serializaEDesserializaSemPerderOsDados() {
        ProdutoJson conversor = new ProdutoJson();
        var produto = new ProdutoJson.Produto("P001", "Caderno", new BigDecimal("15.90"));
        String json = conversor.escrever(produto);
        assertTrue(json.contains("Caderno"));
        assertEquals(produto, conversor.ler(json));
    }
    @Test void rejeitaJsonMalformado() {
        assertThrows(JsonSyntaxException.class, () -> new ProdutoJson().ler("{incompleto"));
    }
}
