/**
 * Componente para mostrar una tarjeta con la información de un miembro del personal
 * Permite ver los detalles del personal y eliminarlo
 */
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CateringServiceService } from '../service/catering-service.service';

@Component({
  selector: 'app-personal-tarjeta',
  standalone: false,
  templateUrl: './personal-tarjeta.component.html',
  styleUrl: './personal-tarjeta.component.css'
})
export class PersonalTarjetaComponent {
  /** Datos del miembro del personal a mostrar en la tarjeta */
  @Input() personal: any;
  /** Evento que se emite cuando se elimina un miembro del personal */
  @Output() personalDeleted = new EventEmitter<number>();

  /**
   * Constructor del componente
   * @param service Servicio para interactuar con la API
   */
  constructor(private service: CateringServiceService) {}

  /**
   * Elimina el miembro del personal actual
   * Muestra una confirmación antes de eliminar y emite un evento al completar
   */
  deletePersonal() {
    if (confirm('¿Estás seguro de que deseas eliminar este miembro del personal?')) {
      this.service.deletePersonal(this.personal.id).subscribe({
        next: () => {
          this.personalDeleted.emit(this.personal.id);
        },
        error: (error) => {
          console.error('Error al eliminar el personal:', error);
          alert('Error al eliminar el personal');
        }
      });
    }
  }
}
