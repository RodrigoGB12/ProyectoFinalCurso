/**
 * Servicio principal para interactuar con la API de catering
 * Proporciona métodos para gestionar platos, clientes, pedidos y personal
 */
import { HttpClient, HttpHeaders, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, throwError } from "rxjs";
import { catchError, tap } from "rxjs/operators";
import { Cliente } from "../../models/cliente.model";

@Injectable({
  providedIn: 'root'
})
export class CateringServiceService {
  /** URL base de la API */
  private apiUrl = 'http://localhost:8080';
  /** Headers por defecto para las peticiones HTTP */
  private headers = new HttpHeaders()
    .set('Content-Type', 'application/json')
    .set('Accept', 'application/json');

  resultado: any

  constructor(private http: HttpClient) {
    console.log("Servicio listo para usar!!!")
  }

  /**
   * Maneja los errores de las peticiones HTTP
   * @param error Error HTTP recibido
   * @returns Observable con el error formateado
   */
  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'Ha ocurrido un error en la aplicación';
    
    if (error.error instanceof ErrorEvent) {
      // Error del lado del cliente
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Error del lado del servidor
      console.error('Error completo:', error);
      console.error('Estado:', error.status);
      console.error('Error body:', error.error);
      
      // Intentar extraer más detalles del error
      if (error.error && typeof error.error === 'object') {
        const errorDetails = Object.entries(error.error)
          .map(([key, value]) => `${key}: ${value}`)
          .join(', ');
        errorMessage = `Error del servidor: ${errorDetails}`;
      } else {
        errorMessage = `Error del servidor: ${error.status} - ${error.error?.message || error.message || 'Error desconocido'}`;
      }
    }
    console.error('Mensaje de error final:', errorMessage);
    return throwError(() => new Error(errorMessage));
  }

  /*  obtenerToken(): HttpHeaders {
     let cabecera:HttpHeaders = new HttpHeaders({
       'Content-Type': 'application/json',
       'withCredentials':'true'
     })
     
     let token;
     //this.http.options(`localhost:8080/menu`,{headers: new HttpHeaders().set('Content-Type', 'application/json').set('withCredentials','true').set('origin','http://localhost:8080')})
     this.http.post(`localhost:8080/auth/login?usuario=usuario&pass=123456`,"",{
       headers: new HttpHeaders()
         .set('Content-Type', 'application/json')
         .set('withCredentials','true')
         .set( 'Authorization', 'Bearer token' )
     
      }).subscribe(
       {
         next:(data:any)=>{
           token = data.token
           console.log(token)
         },
         error:(e) => {
           console.error(e)
         },
         complete:() => {
           console.info('complete');}
       });;

     return new HttpHeaders({
       'Authorization':'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c3VhcmlvIiwiaWF0IjoxNzQxMjczNjk5LCJleHAiOjE3NDEyNzcyOTl9.IzkabW9E4JN9lK7OM-c9r2tL1e4zUcgBkz7_vOJaoCc',
       'Content-Type': 'application/json',
        'withCredentials':'true'
         })
   } */

  /**
   * Obtiene la lista de platos del menú
   * @returns Observable con la lista de platos
   */
  getPlatos(): Observable<any> {
    return this.http.get(`http://localhost:8080/menu`)
  }

  /**
   * Obtiene un plato específico por su ID
   * @param id ID del plato
   * @returns Observable con los datos del plato
   */
  getPlato(id: number): Observable<any> {
    return this.http.get(`http://localhost:8080/menu/${id}`)
  }

  /**
   * Obtiene la lista de alérgenos
   * @returns Observable con la lista de alérgenos
   */
  getAlergenos(): Observable<any> {
    return this.http.get(`http://localhost:8080/alergenos`)
  }

  /**
   * Obtiene las relaciones entre artículos del menú y alérgenos
   * @returns Observable con las relaciones
   */
  getRelacionesAlerPlat(): Observable<any> {
    return this.http.get(`http://localhost:8080/articulos-menu-alergenos`)
  }

  /**
   * Obtiene los alérgenos asociados a un plato específico
   * @param menuData Datos del menú
   * @param nombrePlato Nombre del plato
   * @returns Lista de alérgenos del plato
   */
  getAlergenosPorPlato(menuData: any[], nombrePlato: string) {
    return menuData
      .filter(item => item.articuloMenu.nombre == nombrePlato)
      .map(item => item.alergeno);
  }

  /**
   * Obtiene la lista de clientes
   * @returns Observable con la lista de clientes
   */
  getClientes(): Observable<any> {
    /*         const headers: HttpHeaders = this.obtenerToken()
            console.log("-------------------------------------")
            console.log(this.obtenerToken()) */
    return this.http.get('http://localhost:8080/clientes'/* ,{headers} */);
  }

  /**
   * Obtiene un cliente específico por su ID
   * @param id ID del cliente
   * @returns Observable con los datos del cliente
   */
  getCliente(id: number): Observable<any> {
    /*   const headers: HttpHeaders = this.obtenerToken() */
    return this.http.get(`http://localhost:8080/clientes/${id}`/* ,{headers} */);
  }

  /**
   * Obtiene la lista de trabajadores
   * @returns Observable con la lista de trabajadores
   */
  getTrabajadores(): Observable<any> {
    /*         const headers: HttpHeaders = this.obtenerToken() */
    return this.http.get('http://localhost:8080/personal'/* ,{headers} */);
  }

  /**
   * Obtiene la lista de pedidos
   * @returns Observable con la lista de pedidos
   */
  getPedidos(): Observable<any> {
    /*         const headers: HttpHeaders = this.obtenerToken() */
    return this.http.get('http://localhost:8080/pedidos'/* ,{headers} */);
  }

  /**
   * Obtiene un pedido específico por su ID
   * @param id ID del pedido
   * @returns Observable con los datos del pedido
   */
  getPedido(id: number): Observable<any> {
    /*         const headers: HttpHeaders = this.obtenerToken() */
    return this.http.get(`http://localhost:8080/pedidos/${id}`/* ,{headers} */);
  }

  /**
   * Crea un nuevo alérgeno
   * @param body Datos del alérgeno
   * @returns Observable con la respuesta
   */
  createAlergeno(body: any): Observable<any> {
    return this.http.post(`http://localhost:8080/alergenos`, body);
  }

  /**
   * Actualiza un alérgeno existente
   * @param body Datos actualizados del alérgeno
   * @returns Observable con la respuesta
   */
  updateAlergeno(body: any): Observable<any> {
    return this.http.put(`http://localhost:8080/alergenos`, body);
  }

  /**
   * Elimina un alérgeno
   * @param id ID del alérgeno
   * @returns Observable con la respuesta
   */
  deleteAlergeno(id: number): Observable<any> {
    return this.http.delete(`http://localhost:8080/alergenos?id=${id}`);
  }

  /**
   * Crea una nueva relación entre artículo del menú y alérgeno
   * @param body Datos de la relación
   * @returns Observable con la respuesta
   */
  createArticuloMenuAlergeno(body: any): Observable<any> {
    return this.http.post(`http://localhost:8080/articulos-menu-alergenos`, body);
  }

  /**
   * Actualiza una relación entre artículo del menú y alérgeno
   * @param body Datos actualizados de la relación
   * @returns Observable con la respuesta
   */
  updateArticuloMenuAlergeno(body: any): Observable<any> {
    return this.http.put(`http://localhost:8080/articulos-menu-alergenos`, body);
  }

  /**
   * Elimina una relación entre artículo del menú y alérgeno
   * @param id ID de la relación
   * @returns Observable con la respuesta
   */
  deleteArticuloMenuAlergeno(id: number): Observable<any> {
    return this.http.delete(`http://localhost:8080/articulos-menu-alergenos?id=${id}`);
  }

  /**
   * Crea un nuevo artículo en el menú
   * @param body Datos del artículo
   * @returns Observable con la respuesta
   */
  createMenuItem(body: any): Observable<any> {
    return this.http.post(`http://localhost:8080/menu`, body);
  }

  /**
   * Actualiza un artículo del menú existente
   * @param body Datos actualizados del artículo
   * @returns Observable con la respuesta
   */
  updateMenuItem(body: any): Observable<any> {
    return this.http.put(`http://localhost:8080/menu`, body);
  }

  /**
   * Elimina un artículo del menú
   * @param id ID del artículo
   * @returns Observable con la respuesta
   */
  deleteMenuItem(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/menu/${id}`);
  }

  /**
   * Crea un nuevo artículo de pedido
   * @param body Datos del artículo
   * @returns Observable con la respuesta
   */
  createArticuloPedido(body: any): Observable<any> {
    return this.http.post(`http://localhost:8080/articulos-pedido`, body);
  }

  /**
   * Actualiza un artículo de pedido existente
   * @param body Datos actualizados del artículo
   * @returns Observable con la respuesta
   */
  updateArticuloPedido(body: any): Observable<any> {
    return this.http.put(`http://localhost:8080/articulos-pedido`, body);
  }

  /**
   * Elimina un artículo de pedido
   * @param id ID del artículo
   * @returns Observable con la respuesta
   */
  deleteArticuloPedido(id: number): Observable<any> {
    return this.http.delete(`http://localhost:8080/articulos-pedido?id=${id}`);
  }

  /**
   * Crea un nuevo cliente
   * @param cliente Datos del cliente
   * @returns Observable con la respuesta
   */
  createCliente(cliente: Cliente): Observable<Cliente> {
    console.log('Datos del cliente a enviar:', cliente);
    
    // Asegurarse de que los campos están en el formato correcto
    const clienteFormateado = {
      nombre: cliente.nombre.trim(),
      correo_electronico: cliente.correo_electronico.trim().toLowerCase(),
      telefono: cliente.telefono.trim(),
      direccion: cliente.direccion.trim(),
      dni: cliente.dni.trim().toUpperCase()
    };

    console.log('URL de la API:', `${this.apiUrl}/clientes`);
    console.log('Headers:', this.headers);
    console.log('Datos formateados a enviar:', clienteFormateado);

    return this.http.post<Cliente>(`${this.apiUrl}/clientes`, clienteFormateado, { 
      headers: this.headers 
    }).pipe(
      tap(response => console.log('Respuesta del servidor:', response)),
      catchError(this.handleError)
    );
  }

  /**
   * Actualiza un cliente existente
   * @param body Datos actualizados del cliente
   * @returns Observable con la respuesta
   */
  updateCliente(body: any): Observable<any> {
    return this.http.put(`http://localhost:8080/clientes`, body);
  }

  /**
   * Elimina un cliente
   * @param id ID del cliente
   * @returns Observable con la respuesta
   */
  deleteCliente(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/clientes/${id}`);
  }

  /**
   * Actualiza un pedido existente
   * @param body Datos actualizados del pedido
   * @returns Observable con la respuesta
   */
  updatePedido(body: any): Observable<any> {
    return this.http.put(`http://localhost:8080/pedidos`, body);
  }

  /**
   * Elimina un pedido
   * @param id ID del pedido
   * @returns Observable con la respuesta
   */
  deletePedido(id: number): Observable<any> {
    return this.http.delete(`http://localhost:8080/pedidos?id=${id}`);
  }

  /**
   * Obtiene los detalles de un pedido específico
   * @param id ID del pedido
   * @returns Observable con los detalles del pedido
   */
  getDetallesPedido(id: number): Observable<any> {
    return this.http.get(`http://localhost:8080/pedidos/detalles/${id}`);
  }

  /**
   * Crea un nuevo trabajador
   * @param body Datos del trabajador
   * @returns Observable con la respuesta
   */
  createPersonal(body: any): Observable<any> {
    return this.http.post(`http://localhost:8080/personal`, body);
  }

  /**
   * Actualiza un trabajador existente
   * @param body Datos actualizados del trabajador
   * @returns Observable con la respuesta
   */
  updatePersonal(body: any): Observable<any> {
    return this.http.put(`http://localhost:8080/personal`, body);
  }

  /**
   * Elimina un trabajador
   * @param id ID del trabajador
   * @returns Observable con la respuesta
   */
  deletePersonal(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/personal/${id}`);
  }

  /**
   * Crea un nuevo pedido
   * @param body Datos del pedido
   * @returns Observable con la respuesta
   */
  createPedido(body: any): Observable<any> {
    return this.http.post(`http://localhost:8080/pedidos`, body);
  }
}
