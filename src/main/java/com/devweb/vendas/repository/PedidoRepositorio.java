package com.devweb.vendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devweb.vendas.model.Pedido;
import java.lang.Long;

public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {
}