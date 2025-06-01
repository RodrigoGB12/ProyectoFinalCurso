/**
 * Componente de inicio de sesión personalizado
 * Maneja la autenticación de usuarios y administradores
 */
import { Component, } from '@angular/core';
import {Input,ElementRef } from '@angular/core';
import { CateringServiceService } from '../service/catering-service.service';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-custom',
  standalone: false,
  
  templateUrl: './custom.component.html',
  styleUrl: './custom.component.css'

})


export class CustomComponent {
  // Propiedades del componente
  id:number = 0
  usuarios: any[] = [];
  username: string = '';
  password: string = '';
  encontrado:boolean = false;
  perdido:boolean = false

  /**
   * Constructor del componente
   * Inicializa los servicios necesarios y obtiene la lista de usuarios
   */
  constructor(private el:ElementRef,
    private _platosService: CateringServiceService, 
    private router:Router,
    private authService: AuthService
  ){
    // Obtener la lista de usuarios al inicializar
    this._platosService.getClientes().subscribe(
      (data: any) => {
        this.usuarios = data;
      },
      (error) => {
        console.error('Error al obtener usuarios:', error);
      }
    );
  }


  // Propiedades de personalización del componente
  @Input("colorLogin") colorLogin:string = "#4CAF50"
  @Input("colorLink") colorLink:string = "#4CAF50"
  @Input("colorTextoLogin") colorTextoLogin:string = "white"
  @Input("mostrar") mostrar:boolean = true
  @Input("textoUsername") textoUsername:string = "username"
  @Input("textoPassword") textoPassword:string = "password"
  @Input("tema") tema:string = "claro"
  @Input("texto") texto:string = "Not registered?"
  @Input("link") link:string = "Create an account"

  @Input("colorHover") colorHover:string = "#43A047"
  @Input("colorHoverTexto") colorHoverTexto:string = "white"
   

  /**
   * Maneja el evento cuando el mouse entra en el botón de login
   * Intercambia los colores para el efecto hover
   */
  onMouseEnter(){
    
    let intermediario = this.colorLogin
    this.colorLogin=this.colorHover
    this.colorHover=intermediario

    let intermediario2 = this.colorTextoLogin
    this.colorTextoLogin = this.colorHoverTexto
    this.colorHoverTexto = intermediario2
    
  }

  /**
   * Maneja el evento cuando el mouse sale del botón de login
   * Restaura los colores originales
   */
  onMouseLeave(){
    let intermediario = this.colorLogin
    this.colorLogin = this.colorHover
    this.colorHover = intermediario


    let intermediario2 = this.colorTextoLogin
    this.colorTextoLogin = this.colorHoverTexto
    this.colorHoverTexto = intermediario2

  }

  /**
   * Maneja el proceso de inicio de sesión
   * Verifica las credenciales del usuario y redirige según el tipo de usuario
   */
  inicioSesion() {
    console.log('Usuario:', this.username);
    console.log('Contraseña:', this.password);

    // Verificación de credenciales de administrador
    if(this.username === "admin" && this.password === "admin") {
      this.authService.login(this.username, 'admin');
      this.router.navigate(['/adminDashboard']);
      return;
    }

    // Búsqueda y verificación de usuario normal
    const usuarioEncontrado = this.usuarios.find(user => 
      user.nombre === this.username && 
      user.dni === this.password
    );

    if (usuarioEncontrado) {
      console.log("Usuario encontrado, ID:", usuarioEncontrado.id);
      this.id = usuarioEncontrado.id;
      this.authService.login(this.username, 'user', this.id);
      this.router.navigate(['/cliente/' + this.id]);
    } else {
      console.log("Usuario no encontrado o DNI incorrecto.");
      this.perdido = true;
    }
  }


}
