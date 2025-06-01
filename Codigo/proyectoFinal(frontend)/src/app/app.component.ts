/**
 * Componente raíz de la aplicación
 * Sirve como contenedor principal para todos los demás componentes
 */
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent {
  /** Título de la aplicación */
  title = 'proyectoFinal';
}
