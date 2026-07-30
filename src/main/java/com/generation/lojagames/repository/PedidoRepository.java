package com.generation.lojagames.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.lojagames.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
