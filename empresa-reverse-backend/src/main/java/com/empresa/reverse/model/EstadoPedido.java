package com.empresa.reverse.model;

public enum EstadoPedido {
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
