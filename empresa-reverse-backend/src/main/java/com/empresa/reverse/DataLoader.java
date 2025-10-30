package com.empresa.reverse;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.empresa.reverse.model.Servicio;
import com.empresa.reverse.model.TipoServicio;
import com.empresa.reverse.repository.ServicioRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final ServicioRepository servicioRepository;

    public DataLoader(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (servicioRepository.count() == 0) {

            List<Servicio> servicios = Arrays.asList(
                    new Servicio("Desarrollo Web Básico", "Sitio web de hasta 5 páginas", TipoServicio.DESARROLLO_WEB,
                            new BigDecimal("500.00"), 120),
                    new Servicio("Desarrollo Web Avanzado", "Sitio web con e-commerce", TipoServicio.DESARROLLO_WEB,
                            new BigDecimal("1500.00"), 300),
                    new Servicio("Desarrollo Desktop", "Aplicación de escritorio", TipoServicio.DESARROLLO_DESKTOP,
                            new BigDecimal("1200.00"), 240),
                    new Servicio("Venta de Hardware", "Computadora de escritorio", TipoServicio.VENTA_HARDWARE,
                            new BigDecimal("2000.00"), null),
                    new Servicio("Venta de Software", "Licencia Office 365", TipoServicio.VENTA_SOFTWARE,
                            new BigDecimal("300.00"), null),
                    new Servicio("Integración de Sistemas", "Integración ERP con CRM",
                            TipoServicio.INTEGRACION_SISTEMAS, new BigDecimal("2500.00"), 480),
                    new Servicio("Consultoría Técnica", "Asesoramiento IT", TipoServicio.CONSULTORIA_TECNICA,
                            new BigDecimal("800.00"), 180),
                    new Servicio("Soporte Técnico", "Mantenimiento de hardware y software",
                            TipoServicio.SOPORTE_TECNICO, new BigDecimal("400.00"), 60),
                    new Servicio("Automatización de Procesos", "Automatización con scripts",
                            TipoServicio.AUTOMATIZACION, new BigDecimal("1000.00"), 240),
                    new Servicio("Desarrollo Web Corporativo", "Web corporativa con CMS", TipoServicio.DESARROLLO_WEB,
                            new BigDecimal("900.00"), 200),
                    new Servicio("Desarrollo de App Móvil", "App Android / iOS", TipoServicio.DESARROLLO_DESKTOP,
                            new BigDecimal("2000.00"), 400),
                    new Servicio("Venta de Servidores", "Servidor Rack 1U", TipoServicio.VENTA_HARDWARE,
                            new BigDecimal("5000.00"), null),
                    new Servicio("Licencia Antivirus", "Protección para 10 PCs", TipoServicio.VENTA_SOFTWARE,
                            new BigDecimal("250.00"), null),
                    new Servicio("Integración de API", "Conectar sistemas mediante API",
                            TipoServicio.INTEGRACION_SISTEMAS, new BigDecimal("1200.00"), 180),
                    new Servicio("Consultoría Seguridad", "Auditoría de seguridad IT", TipoServicio.CONSULTORIA_TECNICA,
                            new BigDecimal("1500.00"), 240),
                    new Servicio("Soporte Remoto", "Soporte técnico remoto", TipoServicio.SOPORTE_TECNICO,
                            new BigDecimal("200.00"), 60),
                    new Servicio("Automatización Industrial", "Control de procesos", TipoServicio.AUTOMATIZACION,
                            new BigDecimal("3000.00"), 360),
                    new Servicio("Otros Servicios IT", "Servicios variados", TipoServicio.OTROS,
                            new BigDecimal("500.00"), 60),
                    new Servicio("Desarrollo Web Tienda Online", "E-commerce completo", TipoServicio.DESARROLLO_WEB,
                            new BigDecimal("1800.00"), 320),
                    new Servicio("Venta de Componentes", "RAM, SSD, GPU", TipoServicio.VENTA_HARDWARE,
                            new BigDecimal("800.00"), null));

            servicioRepository.saveAll(servicios);
            System.out.println("Servicios de prueba insertados!");
        }
    }
}
