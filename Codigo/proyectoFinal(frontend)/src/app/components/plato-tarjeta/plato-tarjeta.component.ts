/**
 * Componente para mostrar una tarjeta con la información de un plato
 * Se utiliza para mostrar los detalles de un plato en la interfaz
 */
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CateringServiceService } from '../service/catering-service.service';

@Component({
  selector: 'app-plato-tarjeta',
  standalone: false,
  templateUrl: './plato-tarjeta.component.html',
  styleUrl: './plato-tarjeta.component.css'
})
export class PlatoTarjetaComponent {
  /** Datos del plato a mostrar en la tarjeta */
  @Input() plato: any;
  /** Evento que se emite cuando se elimina un plato */
  @Output() platoDeleted = new EventEmitter<number>();

  /**
   * Constructor del componente
   * @param service Servicio para interactuar con la API
   */
  constructor(private service: CateringServiceService) {}

  /**
   * Elimina el plato actual
   * Muestra una confirmación antes de eliminar y emite un evento al completar
   */
  deletePlato() {
    if (confirm('¿Estás seguro de que deseas eliminar este plato?')) {
      this.service.deleteMenuItem(this.plato.id).subscribe({
        next: () => {
          this.platoDeleted.emit(this.plato.id);
        },
        error: (error) => {
          console.error('Error al eliminar el plato:', error);
          alert('Error al eliminar el plato');
        }
      });
    }
  }
}
