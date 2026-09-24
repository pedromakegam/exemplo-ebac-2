package br.com.pedro.ebac;

import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
class ClienteDAOTest {
    private final IClienteDAO cadastro = new ClienteDAO();
    private final Cliente ana = new Cliente("00000000001", "Ana Exemplo");
    @Test void salvaEBusca() {
        cadastro.salvar(ana); assertEquals(ana, cadastro.buscar(ana.cpf()).orElseThrow());
    }
    @Test void informaQuandoNaoEncontra() { assertTrue(cadastro.buscar(ana.cpf()).isEmpty()); }
    @Test void rejeitaDuplicadoSemSobrescrever() {
        cadastro.salvar(ana);
        assertThrows(IllegalStateException.class, () -> cadastro.salvar(new Cliente(ana.cpf(),"Outro nome")));
        assertEquals(ana, cadastro.buscar(ana.cpf()).orElseThrow());
    }
    @Test void atualizaSomenteClienteExistente() {
        assertThrows(NoSuchElementException.class, () -> cadastro.atualizar(ana));
        cadastro.salvar(ana); Cliente atualizado = new Cliente(ana.cpf(),"Ana Atualizada");
        cadastro.atualizar(atualizado); assertEquals(atualizado, cadastro.buscar(ana.cpf()).orElseThrow());
    }
    @Test void excluiEIndicaAusencia() {
        cadastro.salvar(ana); assertTrue(cadastro.excluir(ana.cpf()));
        assertTrue(cadastro.buscar(ana.cpf()).isEmpty()); assertFalse(cadastro.excluir(ana.cpf()));
    }
}
