package com.empresa.reverse.model;

public enum TipoServicio {
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
