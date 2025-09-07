export interface Servicio {
    id: number;
    nombre: string;
    descripcion: string;
    tipo: string;
    precio: number;
    duracionEstimada: number;
    activo: boolean;
    fechaCreacion: string;
    duracionFormateada: string;
    imagenUrl?:string;
}
