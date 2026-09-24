package br.com.pedro.ebac;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class TabelaTest {
    @Test void leValorDaAnnotationEmRuntime() { assertEquals("clientes", ExemploTabela.nomeTabela(Cliente.class)); }
    @Test void rejeitaClasseNaoAnotada() { assertThrows(IllegalArgumentException.class, () -> ExemploTabela.nomeTabela(String.class)); }
}
