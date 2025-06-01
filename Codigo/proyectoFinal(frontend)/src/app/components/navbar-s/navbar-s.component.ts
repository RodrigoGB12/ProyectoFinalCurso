/**
 * Componente para la barra de navegación segura
 * Proporciona la navegación para usuarios autenticados y funcionalidad de cierre de sesión
 */
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-navbar-s',
  standalone: false,
  templateUrl: './navbar-s.component.html',
  styleUrl: './navbar-s.component.css'
})
export class NavbarSComponent {
  /**
   * Constructor del componente
   * @param router Servicio para la navegación
   * @param authService Servicio de autenticación
   */
  constructor(
    private router: Router,
    private authService: AuthService
  ) {}

  /**
   * Cierra la sesión del usuario actual
   * Elimina las credenciales y redirige a la página de inicio
   */
  logout() {
    this.authService.logout();
    this.router.navigate(['/inicio']);
  }
}
