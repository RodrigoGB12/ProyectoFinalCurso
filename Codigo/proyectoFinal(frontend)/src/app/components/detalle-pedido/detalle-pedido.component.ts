/**
 * Componente para mostrar y gestionar los detalles de un pedido
 * Permite ver los artículos del pedido y añadir nuevos artículos
 */
import { Component } from '@angular/core';
import { CateringServiceService } from '../service/catering-service.service';
import { ActivatedRoute } from '@angular/router';

/**
 * Interfaz que define la estructura de un artículo del menú
 */
interface ArticuloMenu {
  id: number;
  nombre: string;
  precio: number;
  descripcion?: string;
}

/**
 * Interfaz que define la estructura de un detalle de pedido
 */
interface DetallePedido {
  id: number;
  cantidad: number;
  precio: number;
  articuloMenu: ArticuloMenu;
  pedido: any;
  personal: any;
}

@Component({
  selector: 'app-detalle-pedido',
  standalone: false,
  templateUrl: './detalle-pedido.component.html',
  styleUrl: './detalle-pedido.component.css'
})
export class DetallePedidoComponent {
  /** ID del pedido actual */
  id: any;
  /** Datos del pedido */
  pedido: any;
  /** Lista de detalles del pedido */
  detalles: DetallePedido[] = [];
  /** Lista de platos disponibles para añadir */
  platosDisponibles: ArticuloMenu[] = [];
  /** Cantidad del nuevo artículo a añadir */
  cantidadNueva: number = 1;
  /** Plato seleccionado para añadir */
  platoSeleccionado: ArticuloMenu | null = null;

  /**
   * Constructor del componente
   * @param servicio Servicio para interactuar con la API
   * @param activatedRoute Servicio para obtener parámetros de la ruta
   */
  constructor(
    private servicio: CateringServiceService,
    private activatedRoute: ActivatedRoute
  ) {
    this.activatedRoute.params.subscribe(params => {
      this.id = params['id'];
    });
  }

  /**
   * Método del ciclo de vida que se ejecuta al inicializar el componente
   * Carga los datos del pedido, sus detalles y los platos disponibles
   */
  ngOnInit() {
    // Obtener los detalles del pedido
    this.servicio.getPedido(this.id).subscribe({
      next: (data) => {
        this.pedido = data;
        console.log('Pedido recibido:', this.pedido);
      },
      error: (e) => { 
        console.error('Error al obtener el pedido:', e); 
      }
    });

    // Obtener los detalles de los artículos del pedido
    this.servicio.getDetallesPedido(this.id).subscribe({
      next: (data: any) => {
        console.log('Datos brutos de detalles recibidos:', data);
        
        // Verificar si data es un objeto con la propiedad articulosPedido
        if (data && data.articulosPedido && Array.isArray(data.articulosPedido)) {
          this.detalles = data.articulosPedido;
        } else if (Array.isArray(data)) {
          this.detalles = data;
        } else {
          this.detalles = [];
        }
        
        console.log('Detalles procesados:', this.detalles);
        
        // Verificar la estructura de cada detalle
        this.detalles.forEach((detalle, index) => {
          console.log(`Detalle ${index}:`, {
            articuloMenu: detalle.articuloMenu,
            cantidad: detalle.cantidad,
            precio: detalle.precio
          });
        });
      },
      error: (e) => { 
        console.error('Error al obtener los detalles del pedido:', e);
        console.error('Detalles del error:', {
          status: e.status,
          message: e.message,
          error: e.error
        });
      }
    });

    // Obtener todos los platos disponibles
    this.servicio.getPlatos().subscribe({
      next: (data) => {
        this.platosDisponibles = Array.isArray(data) ? data : [];
        console.log('Platos disponibles cargados:', this.platosDisponibles);
      },
      error: (e) => { 
        console.error('Error al obtener los platos disponibles:', e); 
      }
    });
  }

  /**
   * Añade un nuevo artículo al pedido
   * Valida los datos y actualiza la lista de detalles
   */
  agregarArticuloPedido() {
    if (!this.platoSeleccionado || this.cantidadNueva <= 0 || !this.pedido) {
      console.log('Faltan datos necesarios:', {
        platoSeleccionado: this.platoSeleccionado,
        cantidad: this.cantidadNueva,
        pedido: this.pedido
      });
      return;
    }

    // Validar que tenemos todos los datos necesarios
    if (!this.pedido.personal || !this.pedido.personal.id) {
      console.error('No hay personal asignado al pedido');
      return;
    }

    if (!this.platoSeleccionado.precio) {
      console.error('El plato seleccionado no tiene precio');
      return;
    }

    // Construir el objeto según el formato exacto que espera el backend
    const nuevoArticulo = {
      pedido: { 
        id: Number(this.pedido.id) 
      },
      articuloMenu: { 
        id: Number(this.platoSeleccionado.id) 
      },
      cantidad: Number(this.cantidadNueva),
      precio: Number(this.platoSeleccionado.precio),
      personal: { 
        id: Number(this.pedido.personal.id) 
      }
    };

    console.log('Enviando nuevo artículo:', JSON.stringify(nuevoArticulo, null, 2));

    this.servicio.createArticuloPedido(nuevoArticulo).subscribe({
      next: (response) => {
        console.log('Artículo añadido exitosamente:', response);
        // Recargar los detalles del pedido
        this.servicio.getDetallesPedido(this.id).subscribe({
          next: (data: any) => {
            console.log('Detalles actualizados recibidos:', data);
            if (data && data.articulosPedido && Array.isArray(data.articulosPedido)) {
              this.detalles = data.articulosPedido;
            } else if (Array.isArray(data)) {
              this.detalles = data;
            }
            // Resetear los campos
            this.platoSeleccionado = null;
            this.cantidadNueva = 1;
          },
          error: (e) => { 
            console.error('Error al recargar los detalles:', e); 
          }
        });
      },
      error: (e) => { 
        console.error('Error al añadir artículo:', e);
        if (e.error) {
          console.error('Detalles del error:', e.error);
        }
      }
    });
  }
}
