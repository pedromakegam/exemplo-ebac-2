package br.com.rpires;

import br.com.rpires.dao.ClienteDAO;
import br.com.rpires.dao.ProdutoDAO;
import br.com.rpires.dao.VendaDAO;
import br.com.rpires.dao.generic.jdbc.ConnectionFactory;
import br.com.rpires.domain.Cliente;
import br.com.rpires.domain.Produto;
import br.com.rpires.domain.Venda;
import br.com.rpires.exceptions.DAOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NovosCamposDAOTest extends BancoTestBase {
    private final ClienteDAO clientes = new ClienteDAO();
    private final ProdutoDAO produtos = new ProdutoDAO();
    private final VendaDAO vendas = new VendaDAO();

    @Before
    public void limparDadosDeTeste() throws Exception {
        try (Connection c = ConnectionFactory.getConnection(); Statement stm = c.createStatement()) {
            stm.execute("TRUNCATE tb_produto_quantidade, tb_venda, tb_produto, tb_cliente RESTART IDENTITY");
        }
    }

    @Test
    public void cadastrarClienteGravaEmailNoBanco() throws Exception {
        Cliente cliente = cliente();
        assertTrue(clientes.cadastrar(cliente));
        assertEquals(cliente.getEmail(), clientes.consultar(cliente.getCpf()).getEmail());
        assertEquals(cliente.getEmail(), consultarCampo("SELECT email FROM tb_cliente WHERE cpf = ?", cliente.getCpf()));
    }

    @Test
    public void alterarEmailPreservaOsDemaisDados() throws Exception {
        Cliente cliente = cliente();
        clientes.cadastrar(cliente);
        cliente.setEmail("contato.novo@example.com");
        clientes.alterar(cliente);
        Cliente lido = clientes.consultar(cliente.getCpf());
        assertEquals("contato.novo@example.com", lido.getEmail());
        assertEquals(cliente.getId(), lido.getId());
        assertEquals(cliente.getNome(), lido.getNome());
        assertEquals(cliente.getCpf(), lido.getCpf());
        assertEquals(cliente.getTel(), lido.getTel());
        assertEquals(cliente.getEnd(), lido.getEnd());
        assertEquals(cliente.getNumero(), lido.getNumero());
        assertEquals(cliente.getCidade(), lido.getCidade());
        assertEquals(cliente.getEstado(), lido.getEstado());
        assertEquals(lido.getEmail(), consultarCampo("SELECT email FROM tb_cliente WHERE cpf = ?", cliente.getCpf()));
    }

    @Test
    public void listarClientesMapeiaEmailPorAnotacao() throws Exception {
        Cliente cliente = cliente();
        clientes.cadastrar(cliente);
        Collection<Cliente> lista = clientes.buscarTodos();
        assertEquals(1, lista.size());
        assertEquals(cliente.getEmail(), lista.iterator().next().getEmail());
    }

    @Test
    public void cadastrarProdutoGravaCategoriaNoBanco() throws Exception {
        Produto produto = produto();
        assertTrue(produtos.cadastrar(produto));
        assertEquals(produto.getCategoria(), produtos.consultar(produto.getCodigo()).getCategoria());
        assertEquals(produto.getCategoria(), consultarCampo("SELECT categoria FROM tb_produto WHERE codigo = ?", produto.getCodigo()));
    }

    @Test
    public void alterarCategoriaPreservaOsDemaisDados() throws Exception {
        Produto produto = produto();
        produtos.cadastrar(produto);
        produto.setCategoria("Acessórios");
        produtos.alterar(produto);
        Produto lido = produtos.consultar(produto.getCodigo());
        assertEquals("Acessórios", lido.getCategoria());
        assertEquals(produto.getId(), lido.getId());
        assertEquals(produto.getCodigo(), lido.getCodigo());
        assertEquals(produto.getNome(), lido.getNome());
        assertEquals(produto.getDescricao(), lido.getDescricao());
        assertEquals(produto.getValor(), lido.getValor());
        assertEquals(lido.getCategoria(), consultarCampo("SELECT categoria FROM tb_produto WHERE codigo = ?", produto.getCodigo()));
    }

    @Test
    public void listarProdutosMapeiaCategoriaPorAnotacao() throws Exception {
        Produto produto = produto();
        produtos.cadastrar(produto);
        Collection<Produto> lista = produtos.buscarTodos();
        assertEquals(1, lista.size());
        assertEquals(produto.getCategoria(), lista.iterator().next().getCategoria());
    }

    @Test
    public void consultaDeVendaCarregaEmailECategoriaPelasFactories() throws Exception {
        Venda venda = venda("V1");
        assertTrue(vendas.cadastrar(venda));
        Venda lida = vendas.consultar("V1");
        conferirCamposNaVenda(lida);
        assertEquals(new BigDecimal("59.80"), lida.getValorTotal());
        assertEquals(Integer.valueOf(2), lida.getQuantidadeTotalProdutos());
    }

    @Test
    public void listarDuasVendasMantemConexaoAbertaAteTerminarAsAssociacoes() throws Exception {
        Venda primeira = venda("V1");
        vendas.cadastrar(primeira);
        Venda segunda = new Venda();
        segunda.setCodigo("V2");
        segunda.setCliente(primeira.getCliente());
        segunda.setDataVenda(Instant.now());
        segunda.setStatus(Venda.Status.INICIADA);
        segunda.adicionarProduto(primeira.getProdutos().iterator().next().getProduto(), 1);
        vendas.cadastrar(segunda);
        Collection<Venda> lista = vendas.buscarTodos();
        assertEquals(2, lista.size());
        for (Venda item : lista) conferirCamposNaVenda(item);
    }

    @Test
    public void falhaEmItemDesfazVendaSemDeixarCadastroParcial() throws Exception {
        Venda venda = venda("FALHA");
        // Simula produto removido antes da gravação da venda (violação de FK).
        venda.getProdutos().iterator().next().getProduto().setId(-1L);
        assertThrows(DAOException.class, () -> vendas.cadastrar(venda));
        assertNull(venda.getId());
        assertNull(vendas.consultar("FALHA"));
        assertEquals("0", consultarCampo("SELECT count(*) FROM tb_produto_quantidade WHERE id_venda_fk > ?", 0L));
        assertNotNull(clientes.consultar(venda.getCliente().getCpf()));
        assertNotNull(produtos.consultar("P1"));
    }

    @Test
    public void migracaoMantemCadastrosAntigosEPodeSerReexecutada() throws Exception {
        Cliente cliente = cliente();
        Produto produto = produto();
        clientes.cadastrar(cliente);
        produtos.cadastrar(produto);
        // Reconstitui o banco da aula, que ainda não tinha os dois campos.
        try (Connection c = ConnectionFactory.getConnection(); Statement stm = c.createStatement()) {
            stm.execute("ALTER TABLE tb_cliente DROP COLUMN email");
            stm.execute("ALTER TABLE tb_produto DROP COLUMN categoria");
        }
        executarRecurso("migracao-mod30.sql");
        executarRecurso("migracao-mod30.sql");
        Cliente antigo = clientes.consultar(cliente.getCpf());
        Produto antigoProduto = produtos.consultar(produto.getCodigo());
        assertEquals(cliente.getId(), antigo.getId());
        assertEquals(cliente.getNome(), antigo.getNome());
        assertNull(antigo.getEmail());
        assertEquals(produto.getId(), antigoProduto.getId());
        assertEquals(produto.getValor(), antigoProduto.getValor());
        assertNull(antigoProduto.getCategoria());
        antigo.setEmail("atualizado@example.com");
        antigoProduto.setCategoria("Papelaria");
        clientes.alterar(antigo);
        produtos.alterar(antigoProduto);
        assertEquals("atualizado@example.com", clientes.consultar(cliente.getCpf()).getEmail());
        assertEquals("Papelaria", produtos.consultar(produto.getCodigo()).getCategoria());
    }

    private void conferirCamposNaVenda(Venda venda) {
        assertNotNull(venda);
        assertEquals("cliente@example.com", venda.getCliente().getEmail());
        assertEquals(1, venda.getProdutos().size());
        assertEquals("Informática", venda.getProdutos().iterator().next().getProduto().getCategoria());
    }

    private String consultarCampo(String sql, Object chave) throws Exception {
        try (Connection c = ConnectionFactory.getConnection(); PreparedStatement stm = c.prepareStatement(sql)) {
            stm.setObject(1, chave);
            try (ResultSet rs = stm.executeQuery()) {
                assertTrue(rs.next());
                return rs.getString(1);
            }
        }
    }

    private Cliente cliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente de teste");
        cliente.setCpf(11111111111L);
        cliente.setTel(11999999999L);
        cliente.setEnd("Rua de teste");
        cliente.setNumero(10);
        cliente.setCidade("Salvador");
        cliente.setEstado("BA");
        cliente.setEmail("cliente@example.com");
        return cliente;
    }

    private Produto produto() {
        Produto produto = new Produto();
        produto.setCodigo("P1");
        produto.setNome("Mouse");
        produto.setDescricao("Mouse USB para teste");
        produto.setValor(new BigDecimal("29.90"));
        produto.setCategoria("Informática");
        return produto;
    }

    private Venda venda(String codigo) throws Exception {
        Cliente cliente = cliente();
        Produto produto = produto();
        clientes.cadastrar(cliente);
        produtos.cadastrar(produto);
        Venda venda = new Venda();
        venda.setCodigo(codigo);
        venda.setCliente(cliente);
        venda.setDataVenda(Instant.now());
        venda.setStatus(Venda.Status.INICIADA);
        venda.adicionarProduto(produto, 2);
        return venda;
    }
}
