package com.empresa.reverse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.reverse.dto.AuthResponse;
import com.empresa.reverse.dto.LoginRequest;
import com.empresa.reverse.dto.RegistroRequest;
import com.empresa.reverse.dto.UsuarioDTO;
import com.empresa.reverse.model.RolUsuario;
import com.empresa.reverse.model.Usuario;
import com.empresa.reverse.repository.UsuarioRepository;

@Service
@Transactional
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registrar un nuevo usuario
     */
    public AuthResponse registrarUsuario(RegistroRequest request) {
        // Verificar si el email ya existe
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        // Crear nuevo usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(request.getNombre());
        nuevoUsuario.setApellido(request.getApellido());
        nuevoUsuario.setEmail(request.getEmail());
        nuevoUsuario.setTelefono(request.getTelefono());
        
        // Encriptar contraseña
        nuevoUsuario.setPassword(passwordEncoder.encode(request.getPassword()));
        
        // Asignar rol por defecto
        nuevoUsuario.setRol(RolUsuario.CLIENTE);
        nuevoUsuario.setEstado(true);

        // Guardar en la base de datos
        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);

        // Convertir a DTO
        UsuarioDTO usuarioDTO = convertirAUsuarioDTO(usuarioGuardado);

        // Retornar respuesta
        return new AuthResponse(usuarioDTO, "Usuario registrado exitosamente");
    }

    /**
     * Iniciar sesión
     */
    public AuthResponse login(LoginRequest request) {
        // Buscar usuario por email
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas"));

        // Verificar que el usuario esté activo
        if (!usuario.isEstado()) {
            throw new IllegalArgumentException("Usuario inactivo");
        }

        // Verificar contraseña
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        // Convertir a DTO
        UsuarioDTO usuarioDTO = convertirAUsuarioDTO(usuario);

        // Retornar respuesta
        return new AuthResponse(usuarioDTO, "Inicio de sesión exitoso");
    }

    /**
     * Convertir Usuario a UsuarioDTO (sin contraseña)
     */
    private UsuarioDTO convertirAUsuarioDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setEmail(usuario.getEmail());
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setTelefono(usuario.getTelefono());
        dto.setRol(usuario.getRol());
        dto.setFechaCreacion(usuario.getFechaCreacion());
        dto.setEstado(usuario.isEstado());
        return dto;
    }
}