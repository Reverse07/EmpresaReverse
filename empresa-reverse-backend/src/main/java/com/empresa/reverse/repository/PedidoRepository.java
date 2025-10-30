package com.empresa.reverse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.reverse.model.EstadoPedido;
import com.empresa.reverse.model.Pedido;
import com.empresa.reverse.model.Usuario;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Buscar pedidos por cliente
    List<Pedido> findByCliente(Usuario cliente);

    // Buscar pedidos por estado
    List<Pedido> findByEstado(EstadoPedido estado);

    // Buscar pedidos de un cliente con un estado específico
    List<Pedido> findByClienteAndEstado(Usuario cliente, EstadoPedido estado);

}
