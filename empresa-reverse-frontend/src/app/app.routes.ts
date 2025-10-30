import { Routes } from '@angular/router';
import { HomeComponent } from './features/home/home.component';
import { ServiciosPageComponent } from './features/servicios/servicios-page/servicios-page.component';
import { PlanesPageComponent } from './features/planes/planes-page/planes-page.component';
import { NosotrosComponent } from './features/nosotros/nosotros.component';
import { MisionVisionComponent } from './features/mision-vision/mision-vision.component';
import { DevsComponent } from './features/devs/devs.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'servicios', component: ServiciosPageComponent },
  { path: 'planes', component: PlanesPageComponent },
  { path: 'nosotros', component: NosotrosComponent },
  { path: 'mision-vision', component: MisionVisionComponent },
  { path: 'devs', component: DevsComponent}
];