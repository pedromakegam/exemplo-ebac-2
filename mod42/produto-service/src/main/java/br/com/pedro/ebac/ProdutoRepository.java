package br.com.pedro.ebac;

import org.springframework.data.jpa.repository.JpaRepository;
public interface ProdutoRepository extends JpaRepository<Produto,Long> { }
