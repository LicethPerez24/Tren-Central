import { Routes } from '@angular/router';
import { ContactoComponent } from './contacto/contacto.component';
import { CrearCuentaComponent } from './crear-cuenta/crear-cuenta.component';
import { HomeComponent } from './home/home.component';
import NoticiasComponent from './noticias/noticias.component';
import { QuienesSomosComponent } from './quienes-somos/quienes-somos.component';

export const routes: Routes = [
{path: '', component: HomeComponent},
{path: 'noticias', component: NoticiasComponent},
{path: 'contacto', component: ContactoComponent},
{path: 'registrar', component:CrearCuentaComponent},
{path: 'quienes-somos', component: QuienesSomosComponent}
];
