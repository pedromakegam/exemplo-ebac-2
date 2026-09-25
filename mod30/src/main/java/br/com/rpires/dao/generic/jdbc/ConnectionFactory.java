package br.com.rpires.dao.generic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Conexões JDBC do projeto da aula, configuradas sem credenciais no código. */
public class ConnectionFactory {
    private ConnectionFactory() { }

    public static Connection getConnection() throws SQLException {
        String url = obrigatoria("EBAC_DB_URL");
        String usuario = obrigatoria("EBAC_DB_USER");
        String senha = obrigatoria("EBAC_DB_PASSWORD");
        String schema = System.getProperty("ebac.db.schema", System.getenv("EBAC_DB_SCHEMA"));
        if (schema != null && !schema.matches("[a-z_][a-z0-9_]{0,62}")) {
            throw new SQLException("Nome de schema inválido");
        }
        Connection connection = DriverManager.getConnection(url, usuario, senha);
        try {
            if (schema != null) {
                connection.setSchema(schema);
            }
            return connection;
        } catch (SQLException e) {
            try { connection.close(); } catch (SQLException close) { e.addSuppressed(close); }
            throw e;
        }
    }

    private static String obrigatoria(String nome) throws SQLException {
        String valor = System.getenv(nome);
        if (valor == null || valor.isBlank()) {
            throw new SQLException("Configure a variável " + nome + " antes de executar o projeto");
        }
        return valor;
    }
}
