package com.empresa.reverse.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @Column(name = "numero_factura", unique = true, nullable = false, length = 50)
    private String numeroFactura;

    @CreationTimestamp
    @Column(name = "fecha_emision", updatable = false)
    private LocalDateTime fechaEmision;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal igv;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(nullable = false)
    private boolean pagada = false;

    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago;

    @Column(name = "metodo_pago", length = 50)
    private String metodoPago;

    // Constructor personalizado
    public Factura(Pedido pedido, String numeroFactura, BigDecimal subtotal) {
        this.pedido = pedido;
        this.numeroFactura = numeroFactura;
        this.subtotal = subtotal;
        calcularMontos();
    }

    // Calcular IGV y total
    public void calcularMontos() {
        if (subtotal != null) {
            this.igv = subtotal.multiply(BigDecimal.valueOf(0.18))
                    .setScale(2, RoundingMode.HALF_UP);
            this.total = subtotal.add(igv)
                    .setScale(2, RoundingMode.HALF_UP);
        }
    }

    // Marcar como pagada
    public void marcarComoPagada(String metodoPago) {
        this.pagada = true;
        this.fechaPago = LocalDateTime.now();
        this.metodoPago = metodoPago;
    }

    public String getResumenFactura() {
        return String.format("Factura #%s - Pedido #%d - Total: S/%.2f - Estado: %s",
                numeroFactura,
                pedido != null ? pedido.getId() : null,
                total != null ? total : BigDecimal.ZERO,
                pagada ? "Pagada" : "Pendiente");
    }

    @PrePersist
    private void prePersist() {
        if (subtotal != null && (igv == null || total == null)) {
            calcularMontos();
        }
    }
}
