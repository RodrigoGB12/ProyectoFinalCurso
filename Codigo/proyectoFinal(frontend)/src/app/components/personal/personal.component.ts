/**
 * Componente para gestionar la lista de personal
 * Muestra los miembros del personal y permite su eliminación
 */
import { Component } from '@angular/core';
import { CateringServiceService } from '../service/catering-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-personal',
  standalone: false,
  templateUrl: './personal.component.html',
  styleUrl: './personal.component.css'
})
export class PersonalComponent {
  /** Lista de miembros del personal */
  personal: any[] = [];

  /**
   * Constructor del componente
   * @param service Servicio para interactuar con la API
   * @param router Servicio para la navegación
   */
  constructor(private service: CateringServiceService, private router: Router) {
  }

  /**
   * Método del ciclo de vida que se ejecuta al inicializar el componente
   * Carga la lista de personal
   */
  ngOnInit() {
    this.loadPersonal();
  }

  /**
   * Carga la lista de personal desde el servicio
   * Actualiza el array personal con los datos recibidos
   */
  loadPersonal() {
    this.service.getTrabajadores().subscribe({
      next: (data) => {
        this.personal = data;
        console.log(this.personal);
      },
      error: (e) => { console.error(e) },
      complete: () => { console.info('complete'); }
    });
  }

  /**
   * Maneja el evento de eliminación de un miembro del personal
   * Actualiza la lista local eliminando el miembro eliminado
   * @param personalId ID del miembro del personal eliminado
   */
  onPersonalDeleted(personalId: number) {
    this.personal = this.personal.filter(p => p.id !== personalId);
  }
}
