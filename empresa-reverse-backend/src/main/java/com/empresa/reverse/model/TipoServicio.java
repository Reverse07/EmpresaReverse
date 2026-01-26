package com.empresa.reverse.model;

public enum TipoServicio {
    INSTALACION("Instalacion de diferentes recursos"),
    VENTA_HARDWARE("Venta de Hardware"),
    VENTA_SOFTWARE("Venta de Software"),
    DESARROLLO_WEB("Desarrollo Web"),
    DESARROLLO_DESKTOP("Desarrollo Desktop"),
    INTEGRACION_SISTEMAS("Integración de Sistemas"),
    CONSULTORIA_TECNICA("Consultoría Técnica"),
    SOPORTE_TECNICO("Soporte Técnico"),
    AUTOMATIZACION("Automatización"),
    MANTENIMIENTO("Mantenimiento"), // AGREGAR
    SOPORTE("Soporte"), // AGREGAR
    CONFIGURACION("Configuración"), // AGREGAR
    OTROS("Otros");

    private final String descripcion;

    TipoServicio(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}