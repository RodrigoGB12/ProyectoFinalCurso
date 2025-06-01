/**
 * Componente para gestionar la lista de platos
 * Muestra los platos disponibles en el sistema
 */
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CateringServiceService } from '../service/catering-service.service';

@Component({
  selector: 'app-platos',
  standalone: false,
  templateUrl: './platos.component.html',
  styleUrl: './platos.component.css'
})
export class PlatosComponent {
  /** Lista de platos cargados del sistema */
  platos: any[] = [];

  /**
   * Constructor del componente
   * @param _platosService Servicio para interactuar con la API de catering
   * @param router Servicio para la navegación entre rutas
   */
  constructor(private _platosService: CateringServiceService,private router: Router) {
  }

  /**
   * Método del ciclo de vida que se ejecuta al inicializar el componente
   * Carga la lista de platos desde el servicio
   */
  ngOnInit(): void {
    this._platosService.getPlatos().subscribe({
      next: (data) => {
        console.log(data);  
        this.platos = data;  
      },
      error: (e) => {
        console.error(e); 
      },
      complete: () => {
        console.info('Petición completa');
      }
    });
    console.log('Componente de Platos Inicializado');
    console.log(this.platos)
  }

  /**
   * Maneja el evento cuando se elimina un plato
   * Actualiza la lista de platos removiendo el plato eliminado
   * @param platoId ID del plato que fue eliminado
   */
  onPlatoDeleted(platoId: number) {
    this.platos = this.platos.filter(plato => plato.id !== platoId);
  }
}
