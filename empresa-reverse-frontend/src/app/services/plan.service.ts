import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, throwError } from 'rxjs';
import { Servicio } from '../models/servicio.model';
import { Plan } from '../models/plan.model';

@Injectable({
  providedIn: 'root'
})
export class PlanService {

  private apiUrl = 'http://localhost:8080/api/servicios';

  constructor(private http: HttpClient) { }

  getPlanes(): Observable<Plan[]> {
    return this.http.get<Servicio[]>(this.apiUrl).pipe(
      map(servicios => {
        console.log('Servicios obtenidos del backend:', servicios);
        return this.generarPlanes(servicios);
      }),
      catchError(error => {
        console.error('Error en la petición HTTP:', error);
        return throwError(() => error);
      })
    );
  }

  private generarPlanes(servicios: Servicio[]): Plan[] {
    // Función de búsqueda más flexible
    const buscar = (nombre: string): Servicio | undefined => {
      const nombreBusqueda = nombre.toLowerCase().trim();
      
      // Buscar coincidencia exacta primero
      let encontrado = servicios.find(s => 
        s.nombre.toLowerCase().trim() === nombreBusqueda
      );
      
      // Si no encuentra, buscar por coincidencia parcial
      if (!encontrado) {
        encontrado = servicios.find(s => 
          s.nombre.toLowerCase().includes(nombreBusqueda) ||
          nombreBusqueda.includes(s.nombre.toLowerCase())
        );
      }
      
      console.log(`Buscando "${nombre}":`, encontrado || 'NO ENCONTRADO');
      return encontrado;
    };

    // Log de todos los servicios disponibles
    console.log('=== SERVICIOS DISPONIBLES ===');
    servicios.forEach((s, i) => {
      console.log(`${i + 1}. "${s.nombre}" - $${s.precio}`);
    });
    console.log('============================');

    // Definir los planes con búsqueda flexible
    const emprendedor: Plan = {
      nombre: 'Plan Emprendedor',
      descripcion: 'Perfecto para pequeños negocios que inician su transformación digital.',
      serviciosIncluidos: [],
      precioTotal: 0,
      descuento: 0.1,
      precioFinal: 0
    };

    const empresa: Plan = {
      nombre: 'Plan Empresa',
      descripcion: 'Ideal para empresas en crecimiento que buscan expandir su presencia digital.',
      serviciosIncluidos: [],
      precioTotal: 0,
      descuento: 0.15,
      precioFinal: 0
    };

    const corporativo: Plan = {
      nombre: 'Plan Corporativo',
      descripcion: 'Solución completa para grandes empresas con necesidades complejas.',
      serviciosIncluidos: [],
      precioTotal: 0,
      descuento: 0.2,
      precioFinal: 0
    };

    // Buscar servicios para Plan Emprendedor
    const webBasico = buscar('Desarrollo Web Básico') || buscar('Web Básico') || buscar('Básico');
    const consultoria = buscar('Consultoría Técnica') || buscar('Consultoría') || buscar('Consultoria');
    
    if (webBasico) emprendedor.serviciosIncluidos.push(webBasico);
    if (consultoria) emprendedor.serviciosIncluidos.push(consultoria);

    // Buscar servicios para Plan Empresa
    const webAvanzado = buscar('Desarrollo Web Avanzado') || buscar('Web Avanzado') || buscar('Avanzado');
    const integracionApi = buscar('Integración de API') || buscar('API') || buscar('Integracion');
    const soporte = buscar('Soporte Técnico') || buscar('Soporte');
    
    if (webAvanzado) empresa.serviciosIncluidos.push(webAvanzado);
    if (integracionApi) empresa.serviciosIncluidos.push(integracionApi);
    if (soporte) empresa.serviciosIncluidos.push(soporte);

    // Buscar servicios para Plan Corporativo
    const appMovil = buscar('Desarrollo de App Móvil') || buscar('App Móvil') || buscar('Móvil') || buscar('Movil');
    const integracionSistemas = buscar('Integración de Sistemas') || buscar('Sistemas');
    const seguridadConsult = buscar('Consultoría Seguridad') || buscar('Seguridad');
    
    if (appMovil) corporativo.serviciosIncluidos.push(appMovil);
    if (integracionSistemas) corporativo.serviciosIncluidos.push(integracionSistemas);
    if (seguridadConsult) corporativo.serviciosIncluidos.push(seguridadConsult);

    // Si algún plan quedó vacío, asignar servicios por defecto
    if (emprendedor.serviciosIncluidos.length === 0 && servicios.length >= 2) {
      emprendedor.serviciosIncluidos = servicios.slice(0, 2);
      console.warn('Plan Emprendedor: usando primeros 2 servicios por defecto');
    }

    if (empresa.serviciosIncluidos.length === 0 && servicios.length >= 5) {
      empresa.serviciosIncluidos = servicios.slice(2, 5);
      console.warn('Plan Empresa: usando servicios 3-5 por defecto');
    }

    if (corporativo.serviciosIncluidos.length === 0 && servicios.length >= 8) {
      corporativo.serviciosIncluidos = servicios.slice(5, 8);
      console.warn('Plan Corporativo: usando servicios 6-8 por defecto');
    }

    // Calcular precios para cada plan
    const planes = [emprendedor, empresa, corporativo];
    
    planes.forEach(plan => {
      if (plan.serviciosIncluidos.length > 0) {
        plan.precioTotal = plan.serviciosIncluidos
          .map(s => s.precio || 0)
          .reduce((acc, p) => acc + p, 0);
        plan.precioFinal = plan.precioTotal - (plan.precioTotal * plan.descuento);
      }
      
      console.log(`\n${plan.nombre}:`);
      console.log('- Servicios:', plan.serviciosIncluidos.map(s => s.nombre));
      console.log('- Precio Total:', plan.precioTotal);
      console.log('- Descuento:', plan.descuento * 100 + '%');
      console.log('- Precio Final:', plan.precioFinal);
    });

    return planes.filter(p => p.serviciosIncluidos.length > 0);
  }
}