package com.empresa.reverse.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    @Email(message = "Email debe ser valido")
    @NotBlank(message = "Email es obligatorio")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password es obligatorio")
    @Size(min = 6, message = "Password debe tener al menos 6 caracteres")
    private String password;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Apellido es obligatorio")
    private String apellido;

    @Column(length = 20)
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RolUsuario rol = RolUsuario.CLIENTE;

    @CreationTimestamp
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private boolean estado = true;

    // Relaciones
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pedido> pedidos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets = new ArrayList<>();

    @OneToMany(mappedBy = "asesorAsignado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> ticketsAsignados = new ArrayList<>();

    @OneToMany(mappedBy = "destinatario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notificacion> notificaciones = new ArrayList<>();

    // Métodos de utilidad
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public boolean isAdmin() {
        return rol == RolUsuario.ADMIN;
    }

    public boolean isCliente() {
        return rol == RolUsuario.CLIENTE;
    }

    public boolean isAsesor() {
        return rol == RolUsuario.ASESOR;
    }
}
