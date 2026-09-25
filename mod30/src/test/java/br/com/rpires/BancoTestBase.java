package br.com.rpires;

import br.com.rpires.dao.generic.jdbc.ConnectionFactory;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Statement;
import java.util.UUID;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/** Cada classe usa um schema descartável, sem limpar tabelas do usuário. */
public abstract class BancoTestBase {
    private static String schema;
    private static String schemaAnterior;

    @BeforeClass
    public static void prepararBanco() throws Exception {
        schemaAnterior = System.getProperty("ebac.db.schema");
        schema = "teste_mod30_" + UUID.randomUUID().toString().replace("-", "");
        try (Connection c = ConnectionFactory.getConnection(); Statement stm = c.createStatement()) {
            stm.execute("CREATE SCHEMA " + schema);
        }
        System.setProperty("ebac.db.schema", schema);
        executarRecurso("schema-base.sql");
        executarRecurso("migracao-mod30.sql");
    }

    protected static void executarRecurso(String nome) throws Exception {
        try (InputStream in = BancoTestBase.class.getResourceAsStream("/" + nome)) {
            if (in == null) throw new IllegalStateException("SQL ausente: " + nome);
            String sql = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            try (Connection c = ConnectionFactory.getConnection(); Statement stm = c.createStatement()) {
                c.setAutoCommit(false);
                try {
                    stm.execute(sql);
                    c.commit();
                } catch (Exception e) {
                    c.rollback();
                    throw e;
                }
            }
        }
    }

    @AfterClass
    public static void removerBanco() throws Exception {
        try {
            if (schema != null) {
                try (Connection c = ConnectionFactory.getConnection(); Statement stm = c.createStatement()) {
                    stm.execute("DROP SCHEMA IF EXISTS " + schema + " CASCADE");
                }
            }
        } finally {
            if (schemaAnterior == null) System.clearProperty("ebac.db.schema");
            else System.setProperty("ebac.db.schema", schemaAnterior);
            schema = null;
        }
    }
}
