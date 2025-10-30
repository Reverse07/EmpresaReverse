package com.empresa.reverse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.reverse.model.Servicio;
import com.empresa.reverse.model.TipoServicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    // Buscar todos los servicios activos
    List<Servicio> findByActivoTrue();

    // Buscar por tipo de servicio
    List<Servicio> findByTipo(TipoServicio tipo);

}
