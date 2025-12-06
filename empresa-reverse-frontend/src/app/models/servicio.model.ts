export interface Servicio {
  id?: number;
  nombre: string;
  descripcion: string;
  precio: number;
  duracion: number;
  imagenUrl?: string;  // Agregar esta línea
  duracionFormateada?: string;  // Agregar esta línea
  // ... otras propiedades que ya tengas
}