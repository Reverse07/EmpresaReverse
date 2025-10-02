import { Servicio } from './servicio.model';

export interface Plan {
    nombre: string;
    descripcion: string;
    serviciosIncluidos: Servicio[];
    precioTotal: number;
    descuento: number;
    precioFinal: number;
}
