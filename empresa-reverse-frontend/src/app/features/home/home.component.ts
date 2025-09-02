import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ServiceCardComponent } from '../../shared/service-card/service-card.component';
import { VideoCardComponent } from '../../shared/video-card/video-card.component';
import { ServicioService } from '../../services/servicio.service';
import { Servicio } from '../../models/servicio.model';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, ServiceCardComponent, VideoCardComponent],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  servicios: Servicio[] = [];

  constructor(private servicioService: ServicioService) { }

  ngOnInit(): void {
    this.servicioService.getServicios().subscribe({
      next: data => this.servicios = data || [],
      error: err => {
        console.error('Error cargando servicios', err);
        this.servicios = [];
      }
    });
  }
}
