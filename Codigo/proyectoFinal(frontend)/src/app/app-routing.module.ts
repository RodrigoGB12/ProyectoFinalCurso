/**
 * Módulo de enrutamiento de la aplicación
 * Define las rutas y la navegación entre los diferentes componentes
 */
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { PlatosComponent } from './components/platos/platos.component';
import { PlatoComponent } from './components/plato/plato.component';
import { InicioComponent } from './components/inicio/inicio.component';
import { ClienteComponent } from './components/cliente/cliente.component';
import { DetallePedidoComponent } from './components/detalle-pedido/detalle-pedido.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { PersonalComponent } from './components/personal/personal.component';
import { PedidosComponent } from './components/pedidos/pedidos.component';
import { ClientesComponent } from './components/clientes/clientes.component';
import { CreacionComponent } from './components/creacion/creacion.component';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [
  // Rutas públicas
  { path: 'home', component: HomeComponent },
  { path: 'platos', component: PlatosComponent },
  { path: 'plato/:id', component: PlatoComponent },
  { path: 'inicio', component: InicioComponent },

  // Rutas protegidas (requieren autenticación)
  { path: 'cliente/:id', component: ClienteComponent, canActivate: [AuthGuard] },
  { path: 'detalleP/:id', component: DetallePedidoComponent, canActivate: [AuthGuard] },
  { path: 'adminDashboard', component: AdminDashboardComponent, canActivate: [AuthGuard] },
  { path: 'clientes', component: ClientesComponent, canActivate: [AuthGuard] },
  { path: 'personal', component: PersonalComponent, canActivate: [AuthGuard] },
  { path: 'pedidos', component: PedidosComponent, canActivate: [AuthGuard] },
  { path: 'creacion', component: CreacionComponent, canActivate: [AuthGuard] },

  // Ruta por defecto
  { path: '**', pathMatch: 'full', redirectTo: 'inicio' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
