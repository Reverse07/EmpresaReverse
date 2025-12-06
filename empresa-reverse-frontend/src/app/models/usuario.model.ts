export interface Usuario {
  id?: number;
  email: string;
  password?: string;
  nombre: string;
  apellido: string;
  telefono?: string;
  rol: RolUsuario;
  fechaCreacion?: Date;
  estado?: boolean;
}

export enum RolUsuario {
  ADMIN = 'ADMIN',
  CLIENTE = 'CLIENTE',
  ASESOR = 'ASESOR'
}

export interface RegistroRequest {
  email: string;
  password: string;
  nombre: string;
  apellido: string;
  telefono?: string;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface AuthResponse {
  token: string;
  usuario: Usuario;
  mensaje: string;
}