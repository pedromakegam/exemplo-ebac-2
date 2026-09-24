package br.com.pedro.ebac;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
@EnabledIfEnvironmentVariable(named="EBAC_DB_URL", matches="jdbc:postgresql:.*")
class VendaJdbcTest {
    private static Banco banco;
    private static Cliente cliente;
    private static Produto produto;
    @BeforeAll static void preparar() throws Exception {
        String schema="teste_venda_"+UUID.randomUUID().toString().replace("-",""); banco=new Banco(schema);
        try(Connection con=banco.abrir(); Statement st=con.createStatement()) {
            st.execute("CREATE SCHEMA "+schema);
            String ddl=new String(VendaJdbcTest.class.getResourceAsStream("/schema.sql").readAllBytes(),StandardCharsets.UTF_8);
            for(String comando:ddl.split(";")) if(!comando.isBlank()) st.execute(comando);
        }
        cliente=new ClienteDAO(banco).cadastrar(new Cliente(0,"00000000010","Cliente de teste","cliente@example.invalid"));
        produto=new ProdutoDAO(banco).cadastrar(new Produto(0,"VENDA","Produto de teste",new BigDecimal("12.50"),"Teste"));
    }
    @Test void gravaVendaEItensPreservandoPrecoHistorico() throws Exception {
        VendaDAO vendas=new VendaDAO(banco); long id=vendas.cadastrar(cliente.id(),Map.of(produto.id(),2));
        assertEquals(0,new BigDecimal("25.00").compareTo(vendas.total(id)));
        new ProdutoDAO(banco).atualizar(new Produto(produto.id(),produto.codigo(),produto.nome(),new BigDecimal("19.00"),produto.categoria()));
        assertEquals(0,new BigDecimal("25.00").compareTo(vendas.total(id)));
    }
    @Test void erroNoItemDesfazVendaInteira() throws Exception {
        VendaDAO vendas=new VendaDAO(banco); long antes=vendas.quantidadeVendas();
        assertThrows(SQLException.class,()->vendas.cadastrar(cliente.id(),Map.of(-1L,1)));
        assertEquals(antes,vendas.quantidadeVendas());
    }
    @Test void rejeitaQuantidadeInvalidaEClienteAusente() throws Exception {
        VendaDAO vendas=new VendaDAO(banco); long antes=vendas.quantidadeVendas();
        assertThrows(IllegalArgumentException.class,()->vendas.cadastrar(cliente.id(),Map.of(produto.id(),0)));
        assertThrows(SQLException.class,()->vendas.cadastrar(-1,Map.of(produto.id(),1)));
        assertEquals(antes,vendas.quantidadeVendas());
    }
}
