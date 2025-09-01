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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pedidos")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    @NotNull(message = "Cliente es obligatorio")
    private Usuario cliente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servicio_id", nullable = false)
    @NotNull(message = "Servicio es obligatorio")
    private Servicio servicio;
    
    @CreationTimestamp
    @Column(name = "fecha_solicitud")
    private LocalDateTime fechaSolicitud;
    
    @Column(name = "fecha_programada")
    private LocalDateTime fechaProgramada;
    
    @Column(name = "fecha_completada")
    private LocalDateTime fechaCompletada;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPedido estado = EstadoPedido.PENDIENTE;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Dirección es obligatoria")
    private String direccion;
    
    @Column(name = "notas_cliente", columnDefinition = "TEXT")
    private String notasCliente;
    
    @Column(name = "notas_internas", columnDefinition = "TEXT")
    private String notasInternas;
    
    @Column(name = "precio_final", precision = 10, scale = 2)
    private BigDecimal precioFinal;
    
    // Relaciones
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Factura> facturas = new ArrayList<>();
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets = new ArrayList<>();
    
    // Constructores
    public Pedido() {}
    
    public Pedido(Usuario cliente, Servicio servicio, String direccion, String notasCliente) {
        this.cliente = cliente;
        this.servicio = servicio;
        this.direccion = direccion;
        this.notasCliente = notasCliente;
        this.precioFinal = servicio.getPrecio();
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Usuario getCliente() {
        return cliente;
    }
    
    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }
    
    public Servicio getServicio() {
        return servicio;
    }
    
    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
    
    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }
    
    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
    
    public LocalDateTime getFechaProgramada() {
        return fechaProgramada;
    }
    
    public void setFechaProgramada(LocalDateTime fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }
    
    public LocalDateTime getFechaCompletada() {
        return fechaCompletada;
    }
    
    public void setFechaCompletada(LocalDateTime fechaCompletada) {
        this.fechaCompletada = fechaCompletada;
    }
    
    public EstadoPedido getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
        
        // Auto-asignar fecha de completado cuando se marca como completado
        if (estado == EstadoPedido.COMPLETADO && fechaCompletada == null) {
            this.fechaCompletada = LocalDateTime.now();
        }
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getNotasCliente() {
        return notasCliente;
    }
    
    public void setNotasCliente(String notasCliente) {
        this.notasCliente = notasCliente;
    }
    
    public String getNotasInternas() {
        return notasInternas;
    }
    
    public void setNotasInternas(String notasInternas) {
        this.notasInternas = notasInternas;
    }
    
    public BigDecimal getPrecioFinal() {
        return precioFinal;
    }
    
    public void setPrecioFinal(BigDecimal precioFinal) {
        this.precioFinal = precioFinal;
    }
    
    public List<Factura> getFacturas() {
        return facturas;
    }
    
    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }
    
    public List<Ticket> getTickets() {
        return tickets;
    }
    
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
    
    // Métodos de utilidad
    public boolean isPendiente() {
        return estado == EstadoPedido.PENDIENTE;
    }
    
    public boolean isEnProceso() {
        return estado == EstadoPedido.EN_PROCESO;
    }
    
    public boolean isCompletado() {
        return estado == EstadoPedido.COMPLETADO;
    }
    
    public boolean isCancelado() {
        return estado == EstadoPedido.CANCELADO;
    }
    
    public boolean tieneFactura() {
        return !facturas.isEmpty();
    }
    
    // ...existing code...
public Factura getFacturaActiva() {
    return facturas.stream()
            .filter(f -> !f.isPagada())
            .findFirst()
            .orElse(null);
}
// ...existing code...
    
    public String getResumenPedido() {
        return String.format("Pedido #%d - %s - %s", 
                id, 
                servicio.getNombre(), 
                estado.getDescripcion());
    }
    
    @PrePersist
    protected void onCreate() {
        if (precioFinal == null && servicio != null) {
            precioFinal = servicio.getPrecio();
        }
    }
}

// Enum para los estados del pedido
enum EstadoPedido {
    PENDIENTE("Pendiente"),
    EN_PROCESO("En Proceso"),
    COMPLETADO("Completado"),
    CANCELADO("Cancelado");
    
    private final String descripcion;
    
    EstadoPedido(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}
