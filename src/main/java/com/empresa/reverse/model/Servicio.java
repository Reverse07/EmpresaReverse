package com.empresa.reverse.model;


import java.math.BigDecimal;
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
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 200)
    @NotBlank(message = "Nombre del servicio es obligatorio")
    private String nombre;
    
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Tipo de servicio es obligatorio")
    private TipoServicio tipo;
    
    @Column(nullable = false, precision = 10, scale = 2)
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    private BigDecimal precio;
    
    @Column(name = "duracion_estimada")
    private Integer duracionEstimada; // en minutos
    
    @Column(nullable = false)
    private Boolean activo = true;
    
    @CreationTimestamp
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    
    // Relación con pedidos
    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pedido> pedidos = new ArrayList<>();
    
    // Constructores
    public Servicio() {}
    
    public Servicio(String nombre, String descripcion, TipoServicio tipo, BigDecimal precio, Integer duracionEstimada) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.precio = precio;
        this.duracionEstimada = duracionEstimada;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public TipoServicio getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoServicio tipo) {
        this.tipo = tipo;
    }
    
    public BigDecimal getPrecio() {
        return precio;
    }
    
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    
    public Integer getDuracionEstimada() {
        return duracionEstimada;
    }
    
    public void setDuracionEstimada(Integer duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }
    
    public Boolean getActivo() {
        return activo;
    }
    
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    // Métodos de utilidad
    public String getDuracionFormateada() {
        if (duracionEstimada == null) return "No especificada";
        
        int horas = duracionEstimada / 60;
        int minutos = duracionEstimada % 60;
        
        if (horas > 0 && minutos > 0) {
            return horas + "h " + minutos + "m";
        } else if (horas > 0) {
            return horas + " horas";
        } else {
            return minutos + " minutos";
        }
    }
    
    public boolean esDesarrollo() {
        return tipo == TipoServicio.DESARROLLO_WEB || 
            tipo == TipoServicio.DESARROLLO_DESKTOP;
    }
    
    public boolean esVenta() {
        return tipo == TipoServicio.VENTA_HARDWARE || 
            tipo == TipoServicio.VENTA_SOFTWARE;
    }
}

// Enum para los tipos de servicio
enum TipoServicio {
    VENTA_HARDWARE("Venta de Hardware"),
    VENTA_SOFTWARE("Venta de Software"),
    DESARROLLO_WEB("Desarrollo Web"),
    DESARROLLO_DESKTOP("Desarrollo Desktop"),
    INTEGRACION_SISTEMAS("Integración de Sistemas"),
    CONSULTORIA_TECNICA("Consultoría Técnica"),
    SOPORTE_TECNICO("Soporte Técnico"),
    AUTOMATIZACION("Automatización"),
    OTROS("Otros");
    
    private final String descripcion;
    
    TipoServicio(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}

