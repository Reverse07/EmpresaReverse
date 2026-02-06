import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Servicio } from '../../models/servicio.model';

@Component({
  selector: 'app-service-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './service-card.component.html',
  styleUrl: './service-card.component.css'
})
export class ServiceCardComponent {
  @Input() servicio!: Servicio;

  getImageUrl(): string {
    // Si el servicio tiene imagenUrl desde el backend, usarla
    if (this.servicio.imagenUrl) {
      return this.servicio.imagenUrl;
    }

    // Usar placeholders de Unsplash basados en el nombre
    const placeholders: { [key: string]: string } = {
      'internet-hogar': 'https://images.unsplash.com/photo-1544197150-b99a580bb7a8?w=400&h=250&fit=crop',
      'mantenimiento-red': 'https://images.unsplash.com/photo-1558494949-ef010cbdcc31?w=400&h=250&fit=crop',
      'soporte-remoto': 'https://images.unsplash.com/photo-1556155092-8707de31f9c4?w=400&h=250&fit=crop',
      'config-router': 'https://images.unsplash.com/photo-1606904825846-647eb07f5be2?w=400&h=250&fit=crop',
      'camaras-seguridad': 'https://images.unsplash.com/photo-1557597774-9d273605dfa9?w=400&h=250&fit=crop'
    };

    const imageName = this.normalizeServiceName(this.servicio.nombre);
    return placeholders[imageName] || 'https://images.unsplash.com/photo-1451187580459-43490279c0fa?w=400&h=250&fit=crop';
  }

  private normalizeServiceName(nombre: string): string {
    // Convertir "Internet Hogar" → "internet-hogar"
    // "Cámaras Seguridad" → "camaras-seguridad"
    return nombre
      .toLowerCase()
      .normalize('NFD')
      .replace(/[\u0300-\u036f]/g, '') // Quitar acentos
      .replace(/\s+/g, '-')              // Espacios → guiones
      .replace(/[^a-z0-9-]/g, '');       // Quitar caracteres especiales
  }

  onImageError(event: any): void {
    // Si la imagen falla, usar placeholder genérico
    event.target.src = 'https://images.unsplash.com/photo-1451187580459-43490279c0fa?w=400&h=250&fit=crop';
  }
}