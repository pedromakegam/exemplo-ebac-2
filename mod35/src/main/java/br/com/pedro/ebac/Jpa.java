package br.com.pedro.ebac;

import jakarta.persistence.*;
import java.util.Map;
import java.util.UUID;
import java.sql.DriverManager;
import java.util.function.Function;
public final class Jpa {
    private Jpa() { }
    public static EntityManagerFactory prepararTeste() throws Exception {
        String url=ambiente("EBAC_DB_URL"), usuario=ambiente("EBAC_DB_USER"), senha=ambiente("EBAC_DB_PASSWORD");
        String schema="teste_jpa_"+UUID.randomUUID().toString().replace("-","");
        try(var con=DriverManager.getConnection(url,usuario,senha); var st=con.createStatement()) {
            st.execute("CREATE SCHEMA "+schema);
        }
        return Persistence.createEntityManagerFactory("ebac",Map.of(
            "jakarta.persistence.jdbc.url",url,"jakarta.persistence.jdbc.user",usuario,
            "jakarta.persistence.jdbc.password",senha,"hibernate.default_schema",schema,
            "hibernate.hbm2ddl.auto","create","hibernate.show_sql","false"));
    }
    public static <T> T transacao(EntityManagerFactory factory, Function<EntityManager,T> trabalho) {
        EntityManager em=factory.createEntityManager();
        try {
            em.getTransaction().begin(); T resultado=trabalho.apply(em); em.getTransaction().commit(); return resultado;
        } catch(RuntimeException erro) {
            if(em.getTransaction().isActive()) {
                try { em.getTransaction().rollback(); } catch(RuntimeException rollback) { erro.addSuppressed(rollback); }
            }
            throw erro;
        } finally { em.close(); }
    }
    private static String ambiente(String nome) {
        String valor=System.getenv(nome);
        if(valor==null || valor.isBlank()) throw new IllegalStateException("Configure "+nome);
        return valor;
    }
}
