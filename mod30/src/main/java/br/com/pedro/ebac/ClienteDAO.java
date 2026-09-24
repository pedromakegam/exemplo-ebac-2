package br.com.pedro.ebac;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;
import java.util.Objects;
public final class ClienteDAO {
    private final Banco banco;
    public ClienteDAO(Banco banco) { this.banco = Objects.requireNonNull(banco); }
    public Cliente cadastrar(Cliente entidade) throws SQLException {
        try (Connection con = banco.abrir(); PreparedStatement ps = con.prepareStatement("INSERT INTO cliente (cpf, nome, email) VALUES (?, ?, ?) RETURNING id, cpf, nome, email")) {
            preencher(ps, entidade);
            try (ResultSet rs = ps.executeQuery()) { rs.next(); return mapear(rs); }
        }
    }
    public Optional<Cliente> buscar(long id) throws SQLException {
        try (Connection con = banco.abrir(); PreparedStatement ps = con.prepareStatement("SELECT id, cpf, nome, email FROM cliente WHERE id = ?")) {
            ps.setLong(1,id);
            try (ResultSet rs = ps.executeQuery()) { return rs.next() ? Optional.of(mapear(rs)) : Optional.empty(); }
        }
    }
    public List<Cliente> buscarTodos() throws SQLException {
        try (Connection con = banco.abrir(); PreparedStatement ps = con.prepareStatement("SELECT id, cpf, nome, email FROM cliente ORDER BY id"); ResultSet rs = ps.executeQuery()) {
            List<Cliente> lista = new ArrayList<>();
            while (rs.next()) lista.add(mapear(rs));
            return List.copyOf(lista);
        }
    }
    public void atualizar(Cliente entidade) throws SQLException {
        try (Connection con = banco.abrir(); PreparedStatement ps = con.prepareStatement("UPDATE cliente SET cpf = ?, nome = ?, email = ? WHERE id = ?")) {
            preencher(ps, entidade); ps.setLong(4, entidade.id());
            if (ps.executeUpdate() != 1) throw new NoSuchElementException("Cliente não encontrado");
        }
    }
    public boolean excluir(long id) throws SQLException {
        try (Connection con = banco.abrir(); PreparedStatement ps = con.prepareStatement("DELETE FROM cliente WHERE id = ?")) {
            ps.setLong(1,id); return ps.executeUpdate() == 1;
        }
    }
    private void preencher(PreparedStatement ps, Cliente entidade) throws SQLException { ps.setString(1, entidade.cpf()); ps.setString(2, entidade.nome()); ps.setString(3, entidade.email()); }
    private Cliente mapear(ResultSet rs) throws SQLException { return new Cliente(rs.getLong("id"), rs.getString("cpf"), rs.getString("nome"), rs.getString("email")); }
}
