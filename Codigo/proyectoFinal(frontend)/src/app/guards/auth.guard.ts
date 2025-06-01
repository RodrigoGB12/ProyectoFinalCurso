import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    if (!this.authService.isLoggedIn()) {
      this.router.navigate(['/inicio']);
      return false;
    }

    // Verificar rutas específicas para admin
    if (route.url[0]?.path === 'adminDashboard' || 
        route.url[0]?.path === 'clientes' ||
        route.url[0]?.path === 'personal' ||
        route.url[0]?.path === 'pedidos' ||
        route.url[0]?.path === 'creacion') {
      if (!this.authService.isAdmin()) {
        this.router.navigate(['/inicio']);
        return false;
      }
    }

    // Verificar acceso a panel de cliente
    if (route.url[0]?.path === 'cliente') {
      const requestedUserId = Number(route.params['id']);
      const loggedUserId = this.authService.getUserId();
      
      if (this.authService.isAdmin()) {
        return true;
      }
      
      if (requestedUserId !== loggedUserId) {
        this.router.navigate(['/inicio']);
        return false;
      }
    }

    return true;
  }
} 