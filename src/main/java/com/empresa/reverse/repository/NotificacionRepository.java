package com.empresa.reverse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.reverse.model.Notificacion;
import com.empresa.reverse.model.Usuario;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

    // Obtener notificaciones de un usuario
    List<Notificacion> findByDestinatario(Usuario destinatario);

    // Obtener notificaciones no leídas
    List<Notificacion> findByDestinatarioAndLeidaFalse(Usuario destinatario);
}
