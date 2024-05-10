package com.devweb.vendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devweb.vendas.model.ProdutoPedido;


public interface ProdutoPedidoRepositorio extends JpaRepository<ProdutoPedido, Long> {
}
