package com.empresa.reverse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.empresa.reverse.model.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    
    // Buscar solo planes activos
    List<Plan> findByActivoTrue();
    
    // Buscar planes con sus servicios (para evitar N+1 queries)
    @Query("SELECT DISTINCT p FROM Plan p LEFT JOIN FETCH p.serviciosIncluidos WHERE p.activo = true")
    List<Plan> findAllActivosConServicios();
}