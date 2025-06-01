/**
 * Componente para la página principal del sistema
 * Proporciona acceso a las diferentes funcionalidades del sistema
 */
import { Component } from '@angular/core';
import { CateringServiceService } from '../service/catering-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: false,
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  /**
   * Constructor del componente
   * @param _cateringService Servicio para interactuar con la API
   * @param router Servicio para la navegación
   */
  constructor(
    private _cateringService: CateringServiceService,
    private router: Router
  ) {
    // El componente actualmente no tiene funcionalidad adicional
    // La navegación se maneja a través del template HTML
  }
}
