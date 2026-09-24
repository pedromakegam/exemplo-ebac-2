package br.com.pedro.ebac;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;
import java.util.Objects;
public final class ProdutoDAO {
    private final Banco banco;
    public ProdutoDAO(Banco banco) { this.banco = Objects.requireNonNull(banco); }
    public Produto cadastrar(Produto entidade) throws SQLException {
        try (Connection con = banco.abrir(); PreparedStatement ps = con.prepareStatement("INSERT INTO produto (codigo, nome, preco) VALUES (?, ?, ?) RETURNING id, codigo, nome, preco")) {
            preencher(ps, entidade);
            try (ResultSet rs = ps.executeQuery()) { rs.next(); return mapear(rs); }
        }
    }
    public Optional<Produto> buscar(long id) throws SQLException {
        try (Connection con = banco.abrir(); PreparedStatement ps = con.prepareStatement("SELECT id, codigo, nome, preco FROM produto WHERE id = ?")) {
            ps.setLong(1,id);
            try (ResultSet rs = ps.executeQuery()) { return rs.next() ? Optional.of(mapear(rs)) : Optional.empty(); }
        }
    }
    public List<Produto> buscarTodos() throws SQLException {
        try (Connection con = banco.abrir(); PreparedStatement ps = con.prepareStatement("SELECT id, codigo, nome, preco FROM produto ORDER BY id"); ResultSet rs = ps.executeQuery()) {
            List<Produto> lista = new ArrayList<>();
            while (rs.next()) lista.add(mapear(rs));
            return List.copyOf(lista);
        }
    }
    public void atualizar(Produto entidade) throws SQLException {
        try (Connection con = banco.abrir(); PreparedStatement ps = con.prepareStatement("UPDATE produto SET codigo = ?, nome = ?, preco = ? WHERE id = ?")) {
            preencher(ps, entidade); ps.setLong(4, entidade.id());
            if (ps.executeUpdate() != 1) throw new NoSuchElementException("Produto não encontrado");
        }
    }
    public boolean excluir(long id) throws SQLException {
        try (Connection con = banco.abrir(); PreparedStatement ps = con.prepareStatement("DELETE FROM produto WHERE id = ?")) {
            ps.setLong(1,id); return ps.executeUpdate() == 1;
        }
    }
    private void preencher(PreparedStatement ps, Produto entidade) throws SQLException { ps.setString(1, entidade.codigo()); ps.setString(2, entidade.nome()); ps.setBigDecimal(3, entidade.preco()); }
    private Produto mapear(ResultSet rs) throws SQLException { return new Produto(rs.getLong("id"), rs.getString("codigo"), rs.getString("nome"), rs.getBigDecimal("preco")); }
}
