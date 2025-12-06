package com.empresa.reverse.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedidos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // CLIENTE - evitar recursión Usuario <-> Pedido
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    @NotNull(message = "Cliente es obligatorio")
    @JsonIgnore
    private Usuario cliente;

    // SERVICIO - evita recursión Servicio <-> Pedido
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servicio_id", nullable = false)
    @NotNull(message = "Servicio es obligatorio")
    @JsonIgnore
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

    // FACTURAS - evitar ciclos
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Factura> facturas = new ArrayList<>();

    // TICKETS - evitar ciclos
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Ticket> tickets = new ArrayList<>();


    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
        if (estado == EstadoPedido.COMPLETADO && fechaCompletada == null) {
            this.fechaCompletada = LocalDateTime.now();
        }
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

    public Factura getFacturaActiva() {
        return facturas.stream()
                .filter(f -> !f.isPagada())
                .findFirst()
                .orElse(null);
    }

    public String getResumenPedido() {
        return String.format("Pedido #%d - %s - %s",
                id,
                servicio != null ? servicio.getNombre() : "Sin servicio",
                estado.getDescripcion());
    }

    @PrePersist
    protected void onCreate() {
        if (precioFinal == null && servicio != null) {
            precioFinal = servicio.getPrecio();
        }
    }
}
