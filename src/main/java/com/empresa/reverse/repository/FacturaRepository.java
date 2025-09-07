package com.empresa.reverse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.reverse.model.Factura;
import com.empresa.reverse.model.Pedido;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    // Buscar facturas de un pedido
    List<Factura> findByPedido(Pedido pedido);

    // Buscar facturas pagadas o no
    List<Factura> findByPagada(boolean pagada);

}