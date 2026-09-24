package br.com.pedro.ebac;

import org.junit.jupiter.api.Test;
import br.com.rpires.dao.ClienteMapDAO;
import br.com.rpires.domain.Cliente;
import static org.junit.jupiter.api.Assertions.*;
class CadastroClienteTest {
    private Cliente cliente(String nome, String cpf) {
        return new Cliente(nome,cpf,"00000000000","Rua Exemplo","10","Cidade Exemplo","BA");
    }
    @Test void cadastraConsultaELista() {
        var dao = new ClienteMapDAO(); var ana = cliente("Ana Exemplo","00000000001");
        assertTrue(dao.cadastrar(ana)); assertEquals("Ana Exemplo", dao.consultar(1L).getNome());
        assertEquals(1, dao.buscarTodos().size()); assertNull(dao.consultar(2L));
    }
    @Test void naoSubstituiClienteDuplicado() {
        var dao = new ClienteMapDAO(); dao.cadastrar(cliente("Ana","00000000001"));
        assertFalse(dao.cadastrar(cliente("Outro nome","00000000001")));
        assertEquals("Ana", dao.consultar(1L).getNome());
    }
    @Test void alteraEExclui() {
        var dao = new ClienteMapDAO(); dao.cadastrar(cliente("Ana","00000000001"));
        dao.alterar(cliente("Ana Atualizada","00000000001"));
        assertEquals("Ana Atualizada", dao.consultar(1L).getNome());
        dao.excluir(1L); assertNull(dao.consultar(1L)); assertTrue(dao.buscarTodos().isEmpty());
    }
}
