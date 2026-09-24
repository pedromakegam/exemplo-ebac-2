package br.com.pedro.ebac;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.Objects;
public final class JpaDAO<T> {
    private final EntityManager em;
    private final Class<T> tipo;
    public JpaDAO(EntityManager em,Class<T> tipo) {this.em=Objects.requireNonNull(em);this.tipo=Objects.requireNonNull(tipo);}
    public void salvar(T entidade) {em.persist(Objects.requireNonNull(entidade));}
    public Optional<T> buscar(Long id) {return Optional.ofNullable(em.find(tipo,id));}
    public List<T> buscarTodos() {
        var consulta=em.getCriteriaBuilder().createQuery(tipo);consulta.select(consulta.from(tipo));
        return List.copyOf(em.createQuery(consulta).getResultList());
    }
    public T atualizar(T entidade) {return em.merge(Objects.requireNonNull(entidade));}
    public boolean excluir(Long id) {
        T entidade=em.find(tipo,id);if(entidade==null)return false;em.remove(entidade);return true;
    }
}
