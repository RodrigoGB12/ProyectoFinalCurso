/**
 * Servicio de autenticación
 * Maneja el estado de autenticación del usuario, roles y persistencia de sesión
 */
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  /** Estado de autenticación del usuario */
  private isAuthenticated = false;
  /** Rol del usuario (admin/user) */
  private userRole: string | null = null;
  /** ID del usuario autenticado */
  private userId: number | null = null;

  constructor() {
    // Recuperar el estado de autenticación al iniciar el servicio
    this.loadAuthState();
  }

  /**
   * Carga el estado de autenticación desde el almacenamiento local
   * Restaura la sesión del usuario si existe
   */
  private loadAuthState() {
    const authData = localStorage.getItem('authData');
    if (authData) {
      const { isAuthenticated, userRole, userId } = JSON.parse(authData);
      this.isAuthenticated = isAuthenticated;
      this.userRole = userRole;
      this.userId = userId;
    }
  }

  /**
   * Guarda el estado actual de autenticación en el almacenamiento local
   * Permite persistir la sesión entre recargas de página
   */
  private saveAuthState() {
    const authData = {
      isAuthenticated: this.isAuthenticated,
      userRole: this.userRole,
      userId: this.userId
    };
    localStorage.setItem('authData', JSON.stringify(authData));
  }

  /**
   * Inicia sesión con las credenciales proporcionadas
   * @param username Nombre de usuario
   * @param role Rol del usuario (admin/user)
   * @param id ID del usuario (opcional)
   */
  login(username: string, role: string, id: number | null = null) {
    this.isAuthenticated = true;
    this.userRole = role;
    this.userId = id;
    this.saveAuthState();
  }

  /**
   * Cierra la sesión del usuario actual
   * Limpia todos los datos de autenticación
   */
  logout() {
    this.isAuthenticated = false;
    this.userRole = null;
    this.userId = null;
    localStorage.removeItem('authData');
  }

  /**
   * Verifica si hay un usuario autenticado
   * @returns true si hay un usuario autenticado, false en caso contrario
   */
  isLoggedIn(): boolean {
    return this.isAuthenticated;
  }

  /**
   * Obtiene el rol del usuario actual
   * @returns Rol del usuario o null si no hay usuario autenticado
   */
  getUserRole(): string | null {
    return this.userRole;
  }

  /**
   * Obtiene el ID del usuario actual
   * @returns ID del usuario o null si no hay usuario autenticado
   */
  getUserId(): number | null {
    return this.userId;
  }

  /**
   * Verifica si el usuario actual es administrador
   * @returns true si el usuario es administrador, false en caso contrario
   */
  isAdmin(): boolean {
    return this.userRole === 'admin';
  }
} 