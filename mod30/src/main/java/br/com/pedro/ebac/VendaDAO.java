package br.com.pedro.ebac;

import java.sql.*;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
public final class VendaDAO {
    private final Banco banco;
    public VendaDAO(Banco banco) { this.banco = Objects.requireNonNull(banco); }
    public long cadastrar(long clienteId, Map<Long,Integer> itens) throws SQLException {
        if (itens == null || itens.isEmpty()) throw new IllegalArgumentException("Venda sem itens");
        for (var item : itens.entrySet())
            if (item.getKey() == null || item.getValue() == null || item.getValue() <= 0) throw new IllegalArgumentException("Item inválido");
        try (Connection con = banco.abrir()) {
            con.setAutoCommit(false);
            try {
                long vendaId;
                try (PreparedStatement ps = con.prepareStatement("INSERT INTO venda(cliente_id) VALUES (?) RETURNING id")) {
                    ps.setLong(1,clienteId);
                    try (ResultSet rs = ps.executeQuery()) { rs.next(); vendaId = rs.getLong(1); }
                }
                for (var item : itens.entrySet()) {
                    try (PreparedStatement ps = con.prepareStatement("INSERT INTO item_venda(venda_id,produto_id,quantidade,preco_unitario) SELECT ?,id,?,preco FROM produto WHERE id = ?")) {
                        ps.setLong(1,vendaId); ps.setInt(2,item.getValue()); ps.setLong(3,item.getKey());
                        if (ps.executeUpdate() != 1) throw new SQLException("Produto não encontrado");
                    }
                }
                con.commit(); return vendaId;
            } catch (SQLException | RuntimeException erro) {
                try { con.rollback(); } catch (SQLException rollback) { erro.addSuppressed(rollback); }
                throw erro;
            }
        }
    }
    public BigDecimal total(long vendaId) throws SQLException {
        try (Connection con = banco.abrir(); PreparedStatement ps = con.prepareStatement("SELECT COALESCE(SUM(quantidade * preco_unitario),0) FROM item_venda WHERE venda_id=?")) {
            ps.setLong(1,vendaId);
            try (ResultSet rs=ps.executeQuery()) { rs.next(); return rs.getBigDecimal(1); }
        }
    }
    public long quantidadeVendas() throws SQLException {
        try (Connection con=banco.abrir(); Statement ps=con.createStatement(); ResultSet rs=ps.executeQuery("SELECT COUNT(*) FROM venda")) {
            rs.next(); return rs.getLong(1);
        }
    }
}
