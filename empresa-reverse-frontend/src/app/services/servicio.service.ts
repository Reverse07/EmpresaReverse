import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Servicio } from '../models/servicio.model';
import { environment } from '../../environments/environment'; // ✅ Ajusta según tu estructura

@Injectable({ providedIn: 'root' })
export class ServicioService {
  private baseUrl = environment.apiUrl; // http://localhost/api

  constructor(private http: HttpClient) {}

  getServicios(): Observable<Servicio[]> {
    return this.http.get<Servicio[]>(`${this.baseUrl}/servicios`).pipe(
      map(servicios =>
        servicios.map(s => ({
          ...s,
          imagenUrl: this.asignarImagen(s.nombre)
        }))
      )
    );
  }

  private asignarImagen(nombre: string): string {
    const mapa: Record<string, string> = {
      "Desarrollo Web Básico": "web-basica.jpg",
      "Desarrollo Web Avanzado": "web-ecommerce.jpg",
      "Desarrollo Desktop": "desktop-app.jpg",
      "Venta de Hardware": "hardware-upgrade.jpg",
      "Venta de Software": "office365.jpg",
      "Consultoría DevOps": "devops.jpg",
      "Integración de Sistemas": "erp-crm.png",
      "Consultoría Técnica": "consulting.jpg",
      "Soporte Técnico": "maintenance.jpg",
      "Automatización de Procesos": "automation.jpg",
      "Desarrollo Web Corporativo": "corporate-cms.jpg",
      "Desarrollo de App Móvil": "mobile-app.jpg",
      "Venta de Servidores": "server.jpg",
      "Licencia Antivirus": "antivirus.png",
      "Integración de API": "api-integration.jpg",
      "Consultoría Seguridad": "security-audit.jpg",
      "Soporte Remoto": "remote-support.jpg",
      "Automatización Industrial": "process-control.jpg",
      "RAM, SSD, GPU": "hardware-upgrade.jpg",
      "Desarrollo Web Tienda Online": "ecommerce-full.jpg",
      "Otros Servicios IT": "misc.jpg"
    };
    return `/assets/${mapa[nombre] || 'default-service.jpg'}`;
  }
}
