package br.com.pedro.ebac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public final class Banco {
    private final String schema;
    public Banco(String schema) {
        if (schema == null || !schema.matches("[a-z][a-z0-9_]*")) throw new IllegalArgumentException("Schema inválido");
        this.schema = schema;
    }
    public Connection abrir() throws SQLException {
        String url = obrigatoria("EBAC_DB_URL");
        Properties propriedades = new Properties();
        propriedades.setProperty("user", obrigatoria("EBAC_DB_USER"));
        propriedades.setProperty("password", obrigatoria("EBAC_DB_PASSWORD"));
        propriedades.setProperty("connectTimeout", "5");
        propriedades.setProperty("socketTimeout", "10");
        Connection conexao = DriverManager.getConnection(url, propriedades);
        try { conexao.setSchema(schema); return conexao; }
        catch (SQLException erro) { conexao.close(); throw erro; }
    }
    private static String obrigatoria(String nome) {
        String valor = System.getenv(nome);
        if (valor == null || valor.isBlank()) throw new IllegalStateException("Configure " + nome);
        return valor;
    }
}
