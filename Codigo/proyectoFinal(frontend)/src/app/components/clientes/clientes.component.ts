/**
 * Componente para gestionar la lista de clientes
 * Muestra y permite la interacción con los clientes del sistema
 */
import { Component } from '@angular/core';
import { CateringServiceService } from '../service/catering-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-clientes',
  standalone: false,
  templateUrl: './clientes.component.html',
  styleUrl: './clientes.component.css'
})
export class ClientesComponent {
  /** Lista de clientes cargados del sistema */
  clientes: any[] = [];

  /**
   * Constructor del componente
   * @param service Servicio para interactuar con la API de catering
   * @param router Servicio para la navegación entre rutas
   */
  constructor(private service: CateringServiceService, private router: Router) {
  }

  /**
   * Método del ciclo de vida que se ejecuta al inicializar el componente
   * Carga la lista de clientes
   */
  ngOnInit(){
    this.loadClientes();
  }

  /**
   * Carga la lista de clientes desde el servicio
   * Actualiza el array de clientes con los datos recibidos
   */
  loadClientes() {
    this.service.getClientes().subscribe({
      next:(data) => {
        this.clientes = data;
        console.log(this.clientes);
      },
      error:(e) => { console.error(e) },
      complete: () => { console.info('complete'); }           
    });
  }

  /**
   * Maneja el evento cuando se elimina un cliente
   * Actualiza la lista de clientes removiendo el cliente eliminado
   * @param clienteId ID del cliente que fue eliminado
   */
  onClienteDeleted(clienteId: number) {
    this.clientes = this.clientes.filter(cliente => cliente.id !== clienteId);
  }
}
