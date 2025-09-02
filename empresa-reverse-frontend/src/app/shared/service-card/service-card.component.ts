import { Component, Input } from '@angular/core';
import { Servicio } from '../../models/servicio.model';

@Component({
  selector: 'app-service-card',
  imports: [],
  templateUrl: './service-card.component.html',
  styleUrl: './service-card.component.css'
})
export class ServiceCardComponent {

  @Input() servicio!: Servicio;

}
