import { Routes } from '@angular/router';
import { HomeComponent } from './features/home/home.component';
import { ServiciosPageComponent } from './features/servicios/servicios-page/servicios-page.component';
import { PlanesPageComponent } from './features/planes/planes-page/planes-page.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'servicios', component: ServiciosPageComponent },
  { path: 'planes', component: PlanesPageComponent }
];