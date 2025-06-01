/**
 * Módulo principal de la aplicación
 * Configura los componentes, servicios y dependencias necesarias para el funcionamiento de la aplicación
 */
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';
import { PlatosComponent } from './components/platos/platos.component';
import { PlatoTarjetaComponent } from './components/plato-tarjeta/plato-tarjeta.component';
import { CateringServiceService } from './components/service/catering-service.service';
import { HttpClient, HttpClientModule, provideHttpClient } from '@angular/common/http';
import { PlatoComponent } from './components/plato/plato.component';
import { InicioComponent } from './components/inicio/inicio.component';
import { CustomComponent } from './components/login/custom.component';
import { FormsModule } from '@angular/forms';
import { ClienteComponent } from './components/cliente/cliente.component';
import { NavbarSComponent } from './components/navbar-s/navbar-s.component';
import { DetallePedidoComponent } from './components/detalle-pedido/detalle-pedido.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { ClientesComponent } from './components/clientes/clientes.component';
import { PersonalComponent } from './components/personal/personal.component';
import { PedidosComponent } from './components/pedidos/pedidos.component';
import { ClienteTarjetaComponent } from './components/cliente-tarjeta/cliente-tarjeta.component';
import { PersonalTarjetaComponent } from './components/personal-tarjeta/personal-tarjeta.component';
import { PedidoTarjetaComponent } from './components/pedido-tarjeta/pedido-tarjeta.component';
import { FooterComponent } from './components/footer/footer.component';
import { CreacionComponent } from './components/creacion/creacion.component';

@NgModule({
  declarations: [
    // Componentes principales
    AppComponent,
    NavbarComponent,
    HomeComponent,
    InicioComponent,
    FooterComponent,
    AdminDashboardComponent,
    NavbarSComponent,

    // Componentes de gestión de platos
    PlatosComponent,
    PlatoComponent,
    PlatoTarjetaComponent,

    // Componentes de gestión de clientes
    ClientesComponent,
    ClienteComponent,
    ClienteTarjetaComponent,

    // Componentes de gestión de pedidos
    PedidosComponent,
    DetallePedidoComponent,
    PedidoTarjetaComponent,

    // Componentes de gestión de personal
    PersonalComponent,
    PersonalTarjetaComponent,

    // Componentes de autenticación y creación
    CustomComponent,
    CreacionComponent
  ],
  imports: [
    // Módulos de Angular necesarios
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    // Servicios de la aplicación
    CateringServiceService,
    HttpClient
  ],
  bootstrap: [AppComponent] // Componente raíz de la aplicación
})
export class AppModule { }
