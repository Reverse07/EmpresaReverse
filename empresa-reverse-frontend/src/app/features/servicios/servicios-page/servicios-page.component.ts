import { Component, OnInit } from '@angular/core';
import { Servicio } from '../../../models/servicio.model';
import { ServicioService } from '../../../services/servicio.service';
import { ServiceCardComponent } from '../../../shared/service-card/service-card.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-servicios-page',
  imports: [ServiceCardComponent, CommonModule],
  templateUrl: './servicios-page.component.html',
  styleUrl: './servicios-page.component.css'
})
export class ServiciosPageComponent implements OnInit {

  servicios: Servicio[] = [];

  constructor(private servicioService: ServicioService) { }

  ngOnInit(): void {
    this.servicioService.getServicios().subscribe(data => {
      this.servicios = data;
    });
  }

}
