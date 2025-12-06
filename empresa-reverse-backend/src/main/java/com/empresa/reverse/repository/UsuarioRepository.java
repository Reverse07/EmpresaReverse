package com.empresa.reverse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.reverse.model.RolUsuario;
import com.empresa.reverse.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    /**
     * Buscar usuario por email
     */
    Optional<Usuario> findByEmail(String email);
    
    /**
     * Verificar si existe un usuario con ese email
     */
    boolean existsByEmail(String email);
    
    /**
     * Buscar usuarios activos por rol
     */
    List<Usuario> findByRolAndEstadoTrue(RolUsuario rol);
    
    /**
     * Buscar todos los usuarios activos
     */
    List<Usuario> findByEstadoTrue();
}