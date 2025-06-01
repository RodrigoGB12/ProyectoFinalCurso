/**
 * Componente para mostrar los detalles de un cliente específico
 * Muestra la información del cliente y sus pedidos asociados
 */
import { Component } from '@angular/core';
import { CateringServiceService } from '../service/catering-service.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-cliente',
  standalone: false,
  templateUrl: './cliente.component.html',
  styleUrl: './cliente.component.css'
})
export class ClienteComponent {
  /** ID del cliente actual */
  id: any;
  /** Datos del cliente */
  usuario: any;
  /** Lista de todos los pedidos del sistema */
  pedidos: any[] = [];
  /** Lista de pedidos asociados al cliente actual */
  pedidosCliente: any[] = [];

  /**
   * Constructor del componente
   * @param servicio Servicio para interactuar con la API
   * @param activatedRoute Servicio para obtener parámetros de la ruta
   */
  constructor(
    private servicio: CateringServiceService,
    private activatedRoute: ActivatedRoute
  ) {
    this.activatedRoute.params.subscribe(params => {
      this.id = params['id'];
    });
  }

  /**
   * Método del ciclo de vida que se ejecuta al inicializar el componente
   * Carga los datos del cliente y sus pedidos asociados
   */
  ngOnInit() {
    // Obtener los datos del cliente
    this.servicio.getCliente(this.id).subscribe({
      next: (data) => {
        this.usuario = data;
        console.log(this.usuario);
      },
      error: (e) => { console.error(e) },
      complete: () => { console.info('complete'); }
    });
    console.log('Componente Inicializado');

    // Obtener todos los pedidos y filtrar los del cliente actual
    this.servicio.getPedidos().subscribe({
      next: (data) => {
        this.pedidos = data;
        console.log(this.pedidos);
        this.pedidosCliente = this.pedidos.filter(pedido => pedido.cliente.id == this.id);
        console.log(this.pedidosCliente);
      },
      error: (e) => { console.error(e) },
      complete: () => { console.info('complete'); }
    });
  }
}
