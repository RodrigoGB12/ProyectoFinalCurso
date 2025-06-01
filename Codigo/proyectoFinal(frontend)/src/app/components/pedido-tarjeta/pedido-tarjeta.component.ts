/**
 * Componente para mostrar una tarjeta con la información de un pedido
 * Permite ver los detalles del pedido y eliminarlo
 */
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CateringServiceService } from '../service/catering-service.service';

@Component({
  selector: 'app-pedido-tarjeta',
  standalone: false,
  templateUrl: './pedido-tarjeta.component.html',
  styleUrl: './pedido-tarjeta.component.css'
})
export class PedidoTarjetaComponent {
  /** Datos del pedido a mostrar en la tarjeta */
  @Input() pedido: any;
  /** Evento que se emite cuando se elimina un pedido */
  @Output() pedidoDeleted = new EventEmitter<number>();

  /**
   * Constructor del componente
   * @param service Servicio para interactuar con la API
   */
  constructor(private service: CateringServiceService) {}

  /**
   * Elimina el pedido actual
   * Muestra una confirmación antes de eliminar y emite un evento al completar
   */
  deletePedido() {
    if (confirm('¿Estás seguro de que deseas eliminar este pedido?')) {
      this.service.deletePedido(this.pedido.id).subscribe({
        next: () => {
          this.pedidoDeleted.emit(this.pedido.id);
        },
        error: (error) => {
          console.error('Error al eliminar el pedido:', error);
          alert('Error al eliminar el pedido');
        }
      });
    }
  }
}
