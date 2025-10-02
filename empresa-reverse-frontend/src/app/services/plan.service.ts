import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
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
      map(servicios => this.generarPlanes(servicios))
    );
  }

  private generarPlanes(servicios: Servicio[]): Plan[] {
    const buscar = (nombre: string) =>
      servicios.find(s => s.nombre.toLowerCase() === nombre.toLowerCase());

    const emprendedor: Plan = {
      nombre: 'Plan Emprendedor',
      descripcion: 'Perfecto para pequeños negocios.',
      serviciosIncluidos: [
        buscar('Desarrollo Web Básico')!,
        buscar('Consultoría Técnica')!
      ],
      precioTotal: 0,
      descuento: 0.1,
      precioFinal: 0
    };

    const empresa: Plan = {
      nombre: 'Plan Empresa',
      descripcion: 'Ideal para empresas en expansión digital.',
      serviciosIncluidos: [
        buscar('Desarrollo Web Avanzado')!,
        buscar('Integración de API')!,
        buscar('Soporte Técnico')!
      ],
      precioTotal: 0,
      descuento: 0.15,
      precioFinal: 0
    };

    const corporativo: Plan = {
      nombre: 'Plan Corporativo',
      descripcion: 'Pensado para grandes empresas.',
      serviciosIncluidos: [
        buscar('Desarrollo de App Móvil')!,
        buscar('Integración de Sistemas')!,
        buscar('Consultoría Seguridad')!
      ],
      precioTotal: 0,
      descuento: 0.2,
      precioFinal: 0
    };

    [emprendedor, empresa, corporativo].forEach(plan => {
      plan.precioTotal = plan.serviciosIncluidos
        .map(s => s.precio)
        .reduce((acc, p) => acc + p, 0);
      plan.precioFinal = plan.precioTotal - plan.precioTotal * plan.descuento;
    });

    return [emprendedor, empresa, corporativo];
  }
}
