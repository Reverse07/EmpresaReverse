package com.empresa.reverse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.reverse.model.EstadoTicket;
import com.empresa.reverse.model.Ticket;
import com.empresa.reverse.model.Usuario;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Tickets creados por un usuario
    List<Ticket> findByUsuario(Usuario usuario);

    // Tickets asignados a un asesor
    List<Ticket> findByAsesorAsignado(Usuario asesor);

    // Tickets por estado
    List<Ticket> findByEstado(EstadoTicket estado);
}
