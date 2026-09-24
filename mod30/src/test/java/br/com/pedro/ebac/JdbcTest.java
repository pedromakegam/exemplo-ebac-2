package br.com.pedro.ebac;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import java.nio.charset.StandardCharsets;
import java.math.BigDecimal;
import java.sql.*;
import java.util.UUID;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
@EnabledIfEnvironmentVariable(named="EBAC_DB_URL", matches="jdbc:postgresql:.*")
class JdbcTest {
    private static Banco banco;
    private ClienteDAO clientes;
    private ProdutoDAO produtos;
    @BeforeAll static void prepararSchema() throws Exception {
        String schema = "teste_mod30_" + UUID.randomUUID().toString().replace("-", "");
        banco = new Banco(schema);
        try (Connection con = banco.abrir(); Statement st = con.createStatement()) {
            st.execute("CREATE SCHEMA " + schema);
            String ddl = new String(JdbcTest.class.getResourceAsStream("/schema.sql").readAllBytes(), StandardCharsets.UTF_8);
            for (String comando : ddl.split(";")) if (!comando.isBlank()) st.execute(comando);
        }
    }
    @BeforeEach void prepararDaos() { clientes = new ClienteDAO(banco); produtos = new ProdutoDAO(banco); }
    @Test void clienteCompletaCrudComBuscaDeTodosEUpdate() throws Exception {
        Cliente c = clientes.cadastrar(new Cliente(0,"00000000001","Ana Exemplo", "ana@example.invalid"));
        assertTrue(c.id()>0); assertEquals(c,clientes.buscar(c.id()).orElseThrow());
        assertTrue(clientes.buscarTodos().contains(c));
        Cliente alterado = new Cliente(c.id(),c.cpf(),"Ana Atualizada", "atualizada@example.invalid"); clientes.atualizar(alterado);
        assertEquals(alterado,clientes.buscar(c.id()).orElseThrow());
        assertTrue(clientes.excluir(c.id())); assertTrue(clientes.buscar(c.id()).isEmpty()); assertFalse(clientes.excluir(c.id()));
    }
    @Test void produtoCompletaCrud() throws Exception {
        Produto p = produtos.cadastrar(new Produto(0,"CRUD","Caderno",new BigDecimal("15.90"), "Papelaria"));
        assertEquals(p,produtos.buscar(p.id()).orElseThrow()); assertTrue(produtos.buscarTodos().contains(p));
        Produto alterado = new Produto(p.id(),p.codigo(),"Caderno grande",new BigDecimal("18.90"), "Escritório"); produtos.atualizar(alterado);
        assertEquals(alterado,produtos.buscar(p.id()).orElseThrow());
        assertTrue(produtos.excluir(p.id())); assertTrue(produtos.buscar(p.id()).isEmpty());
    }
    @Test void protegeCodigoDuplicado() throws Exception {
        Produto p = new Produto(0,"DUP","Teste",new BigDecimal("1.00"), "Papelaria");
        produtos.cadastrar(p); assertThrows(SQLException.class,()->produtos.cadastrar(p));
    }
    @Test void parametroComApostrofoNaoViraSql() throws Exception {
        Produto p = produtos.cadastrar(new Produto(0,"ASP","D'Água; --",new BigDecimal("1.00"), "Papelaria"));
        assertEquals("D'Água; --",produtos.buscar(p.id()).orElseThrow().nome());
        assertFalse(produtos.buscarTodos().isEmpty());
    }
    @Test void atualizarRegistroAusenteInformaFalha() {
        assertThrows(NoSuchElementException.class,()->clientes.atualizar(new Cliente(-1,"00000000099","Ausente", "ana@example.invalid")));
        assertThrows(NoSuchElementException.class,()->produtos.atualizar(new Produto(-1,"AUS","Ausente",BigDecimal.ONE, "Papelaria")));
    }
}
