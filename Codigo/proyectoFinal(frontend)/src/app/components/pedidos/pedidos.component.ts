/**
 * Componente para gestionar la lista de pedidos
 * Muestra y permite la interacción con los pedidos del sistema
 */
import { Component } from '@angular/core';
import { CateringServiceService } from '../service/catering-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pedidos',
  standalone: false,
  templateUrl: './pedidos.component.html',
  styleUrl: './pedidos.component.css'
})
export class PedidosComponent {
  /** Lista de pedidos cargados del sistema */
  pedidos: any[] = [];

  /**
   * Constructor del componente
   * @param service Servicio para interactuar con la API de catering
   * @param router Servicio para la navegación entre rutas
   */
  constructor(private service: CateringServiceService, private router: Router) {
  }

  /**
   * Método del ciclo de vida que se ejecuta al inicializar el componente
   * Carga la lista de pedidos
   */
  ngOnInit() {
    this.loadPedidos();
  }

  /**
   * Carga la lista de pedidos desde el servicio
   * Actualiza el array de pedidos con los datos recibidos
   */
  loadPedidos() {
    this.service.getPedidos().subscribe({
      next: (data) => {
        this.pedidos = data;
        console.log(this.pedidos);
      },
      error: (e) => { console.error(e) },
      complete: () => { console.info('complete'); }
    });
  }

  /**
   * Maneja el evento cuando se elimina un pedido
   * Actualiza la lista de pedidos removiendo el pedido eliminado
   * @param pedidoId ID del pedido que fue eliminado
   */
  onPedidoDeleted(pedidoId: number) {
    this.pedidos = this.pedidos.filter(pedido => pedido.id !== pedidoId);
  }
}
