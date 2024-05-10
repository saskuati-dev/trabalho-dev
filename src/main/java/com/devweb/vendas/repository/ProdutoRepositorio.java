package com.devweb.vendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devweb.vendas.model.Produto;


public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {
    Produto findByCodigo(String codigo);
    List<Produto> findByDescricaoContaining(String descricao);
    void deleteByCodigo(String codigo);

}