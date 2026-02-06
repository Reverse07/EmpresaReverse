import { Servicio } from './servicio.model';

export interface Plan {
  id: number;
  nombre: string;
  descripcion: string;
  precioBase: number;
  descuento: number;
  precioFinal: number;
  activo: boolean;
  createdAt?: string;
  serviciosIncluidos: Servicio[];
}