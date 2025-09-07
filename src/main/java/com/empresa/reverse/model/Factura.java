package com.empresa.reverse.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    // Relación con Pedido (muchas facturas pueden pertenecer a un pedido)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @Column(name = "numero_factura", unique = true, nullable = false, length = 50)
    private String numeroFactura;

    @Column(name = "fecha_emision")
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

    public Factura(Pedido pedido, String numeroFactura, LocalDateTime fechaEmision,
            BigDecimal subtotal, BigDecimal igv, BigDecimal total,
            boolean pagada, LocalDateTime fechaPago, String metodoPago) {
        this.pedido = pedido;
        this.numeroFactura = numeroFactura;
        this.fechaEmision = fechaEmision;
        this.subtotal = subtotal;
        this.igv = igv;
        this.total = total;
        this.pagada = pagada;
        this.fechaPago = fechaPago;
        this.metodoPago = metodoPago;
    }

    // Métodos de utilidad

    /**
     * Calcula el IGV y el total a partir del subtotal.
     * El IGV estándar en Perú es 18%.
     */
    public void calcularMontos() {
        if (subtotal != null) {
            this.igv = subtotal.multiply(BigDecimal.valueOf(0.18))
                    .setScale(2, RoundingMode.HALF_UP);
            this.total = subtotal.add(igv)
                    .setScale(2, RoundingMode.HALF_UP);
        }
    }

    /**
     * Marca la factura como pagada y registra la fecha de pago.
     */
    public void Pagada(String metodoPago) {
        this.pagada = true;
        this.fechaPago = LocalDateTime.now();
        this.metodoPago = metodoPago;
    }

    /**
     * Devuelve un resumen de la factura.
     */
    public String getResumenFactura() {
        return String.format("Factura #%s - Pedido #%d - Total: S/%.2f - Estado: %s",
                numeroFactura,
                pedido != null ? pedido.getId() : null,
                total != null ? total : BigDecimal.ZERO,
                pagada ? "Pagada" : "Pendiente");
    }
}