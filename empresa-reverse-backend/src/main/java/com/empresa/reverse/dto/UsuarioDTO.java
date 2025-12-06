package com.empresa.reverse.dto;

import java.time.LocalDateTime;

import com.empresa.reverse.model.RolUsuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para enviar información del usuario (sin contraseña)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    
    private Long id;
    private String email;
    private String nombre;
    private String apellido;
    private String telefono;
    private RolUsuario rol;
    private LocalDateTime fechaCreacion;
    private boolean estado;
}
