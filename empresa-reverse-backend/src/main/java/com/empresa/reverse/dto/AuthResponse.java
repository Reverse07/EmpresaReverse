package com.empresa.reverse.dto;

import com.empresa.reverse.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para la respuesta de autenticación
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    
    private String token;  // Aunque no uses JWT, puedes retornar un sessionId o dejarlo vacío
    private UsuarioDTO usuario;
    private String mensaje;
    
    // Constructor sin token
    public AuthResponse(UsuarioDTO usuario, String mensaje) {
        this.token = "";
        this.usuario = usuario;
        this.mensaje = mensaje;
    }
}