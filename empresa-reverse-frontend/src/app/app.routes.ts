import { Routes } from '@angular/router';
import { HomeComponent } from './features/home/home.component';
import { ServiciosPageComponent } from './features/servicios/servicios-page/servicios-page.component';
import { PlanesPageComponent } from './features/planes/planes-page/planes-page.component';
import { NosotrosComponent } from './features/nosotros/nosotros.component';
import { MisionVisionComponent } from './features/mision-vision/mision-vision.component';
import { DevsComponent } from './features/devs/devs.component';
import { ContactosComponent } from './features/contactos/contactos.component';
import { RegistroComponent } from './features/auth/registro/registro.component';
import { LoginComponent } from './features/auth/login/login.component';


export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'servicios', component: ServiciosPageComponent },
  { path: 'planes', component: PlanesPageComponent },
  { path: 'nosotros', component: NosotrosComponent },
  { path: 'mision-vision', component: MisionVisionComponent },
  { path: 'devs', component: DevsComponent},
  { path: 'contactos', component: ContactosComponent },
  { path: 'registro', component: RegistroComponent },
  { path: 'login', component: LoginComponent }
];