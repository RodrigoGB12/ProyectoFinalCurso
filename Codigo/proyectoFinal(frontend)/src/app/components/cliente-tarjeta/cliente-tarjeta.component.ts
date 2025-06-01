/**
 * Componente para mostrar una tarjeta con la información de un cliente
 * Permite ver los detalles del cliente y eliminarlo
 */
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CateringServiceService } from '../service/catering-service.service';

@Component({
  selector: 'app-cliente-tarjeta',
  standalone: false,
  templateUrl: './cliente-tarjeta.component.html',
  styleUrl: './cliente-tarjeta.component.css'
})
export class ClienteTarjetaComponent {
  /** Datos del cliente a mostrar en la tarjeta */
  @Input() cliente: any;
  /** Evento que se emite cuando se elimina un cliente */
  @Output() clienteDeleted = new EventEmitter<number>();

  /**
   * Constructor del componente
   * @param service Servicio para interactuar con la API
   */
  constructor(private service: CateringServiceService) {}

  /**
   * Elimina el cliente actual
   * Muestra una confirmación antes de eliminar y emite un evento al completar
   */
  deleteCliente() {
    if (confirm('¿Estás seguro de que deseas eliminar este cliente?')) {
      this.service.deleteCliente(this.cliente.id).subscribe({
        next: () => {
          this.clienteDeleted.emit(this.cliente.id);
        },
        error: (error) => {
          console.error('Error al eliminar el cliente:', error);
          alert('Error al eliminar el cliente');
        }
      });
    }
  }
}
