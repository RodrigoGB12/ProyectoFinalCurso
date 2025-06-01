/**
 * Componente para la creación de diferentes entidades del sistema
 * Permite crear clientes, artículos, pedidos y personal
 */
import { Component, OnInit } from '@angular/core';
import { CateringServiceService } from '../service/catering-service.service';
import { Router } from '@angular/router';
import { catchError, finalize } from 'rxjs/operators';
import { of } from 'rxjs';
import { Cliente } from '../../models/cliente.model';

@Component({
  selector: 'app-creacion',
  standalone: false,
  templateUrl: './creacion.component.html',
  styleUrls: ['./creacion.component.css']
})
export class CreacionComponent implements OnInit {
  /** Tipo de entidad seleccionada para crear */
  tipoSeleccionado: string = '';
  /** Indica si hubo un error con el personal */
  errorPersonal = false;
  /** Indica si está en proceso de carga */
  isLoading = false;
  /** Mensaje de error actual */
  errorMessage = '';
  /** Indica si se ha intentado enviar el formulario */
  submitted = false;

  /** Datos del cliente a crear */
  cliente: Cliente = {
    nombre: '',
    correo_electronico: '',
    telefono: '',
    direccion: '',
    dni: ''
  };

  /** Datos del artículo a crear */
  articulo = {
    nombre: '',
    descripcion: '',
    precio: 0,
    imagen: ''
  };

  /** Datos del pedido a crear */
  pedido = {
    cliente: '',
    fecha: '',
    monto: 0,
    personal: ''
  };

  /** Datos del personal a crear */
  personal = {
    nombre: '',
    correo_electronico: '',
    telefono: '',
    dni: '',
    rol: ''
  };

  /**
   * Constructor del componente
   * @param service Servicio para interactuar con la API
   * @param router Servicio para la navegación
   */
  constructor(private service: CateringServiceService, private router: Router) {}

  ngOnInit() {
    // Inicialización si es necesaria
  }

  /**
   * Valida los datos del cliente antes de enviarlos
   * @returns true si los datos son válidos, false en caso contrario
   */
  private validarCliente(): boolean {
    this.submitted = true;
    this.errorMessage = '';

    // Validar campos vacíos
    if (!this.cliente.nombre?.trim()) {
      this.errorMessage = 'El nombre es obligatorio';
      return false;
    }
    if (!this.cliente.correo_electronico?.trim()) {
      this.errorMessage = 'El correo electrónico es obligatorio';
      return false;
    }
    if (!this.cliente.telefono?.trim()) {
      this.errorMessage = 'El teléfono es obligatorio';
      return false;
    }
    if (!this.cliente.direccion?.trim()) {
      this.errorMessage = 'La dirección es obligatoria';
      return false;
    }
    if (!this.cliente.dni?.trim()) {
      this.errorMessage = 'El DNI es obligatorio';
      return false;
    }

    // Validar formato de correo
    const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    if (!emailRegex.test(this.cliente.correo_electronico.trim())) {
      this.errorMessage = 'Por favor, ingrese un correo electrónico válido';
      return false;
    }

    // Validar formato de DNI (8 números seguidos de una letra)
    const dniRegex = /^[0-9]{8}[A-Z]$/;
    if (!dniRegex.test(this.cliente.dni.trim().toUpperCase())) {
      this.errorMessage = 'Por favor, ingrese un DNI válido (8 números seguidos de una letra mayúscula)';
      return false;
    }

    // Validar que el teléfono solo contenga números
    const phoneRegex = /^[0-9]+$/;
    if (!phoneRegex.test(this.cliente.telefono.trim().replace(/\s+/g, ''))) {
      this.errorMessage = 'El teléfono solo debe contener números';
      return false;
    }

    return true;
  }

  /**
   * Crea un nuevo cliente en el sistema
   * Valida los datos y maneja los errores
   */
  private crearCliente() {
    if (!this.validarCliente()) {
      this.isLoading = false;
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';

    // Limpiar y formatear los datos antes de enviar
    const clienteParaEnviar: Cliente = {
      nombre: this.cliente.nombre.trim(),
      correo_electronico: this.cliente.correo_electronico.trim().toLowerCase(),
      telefono: this.cliente.telefono.trim(),
      direccion: this.cliente.direccion.trim(),
      dni: this.cliente.dni.trim().toUpperCase()
    };

    console.log('Intentando crear cliente con datos:', JSON.stringify(clienteParaEnviar, null, 2));

    this.service.createCliente(clienteParaEnviar).pipe(
      catchError(error => {
        console.error('Error detallado al crear cliente:', error);
        
        // Intentar extraer un mensaje más específico del error
        if (error.error?.message) {
          this.errorMessage = `Error: ${error.error.message}`;
        } else if (error.message) {
          this.errorMessage = error.message;
        } else {
          this.errorMessage = 'Error al crear el cliente. Por favor, intente nuevamente.';
        }
        
        return of(null);
      }),
      finalize(() => {
        this.isLoading = false;
      })
    ).subscribe(response => {
      if (response) {
        console.log('Cliente creado exitosamente:', response);
        this.router.navigate(['/clientes']);
      } else {
        console.log('No se recibió respuesta del servidor');
      }
    });
  }

  /**
   * Crea un nuevo artículo en el menú
   * Maneja los errores y redirecciona al completar
   */
  private crearArticulo() {
    this.service.createMenuItem(this.articulo).pipe(
      catchError(error => {
        this.errorMessage = 'Error al crear el artículo: ' + error.message;
        return of(null);
      }),
      finalize(() => this.isLoading = false)
    ).subscribe(response => {
      if (response) {
        this.router.navigate(['/articulos']);
      }
    });
  }

  /**
   * Crea un nuevo pedido en el sistema
   * Valida la existencia del cliente y personal antes de crear
   */
  private crearPedido() {
    this.service.getClientes().pipe(
      catchError(error => {
        this.errorMessage = 'Error al obtener clientes: ' + error.message;
        return of([]);
      })
    ).subscribe(clientes => {
      const clienteEncontrado = clientes.find((c: any) => 
        c.nombre.toLowerCase() === this.pedido.cliente.toLowerCase()
      );

      if (!clienteEncontrado) {
        this.errorMessage = 'Cliente no encontrado';
        this.isLoading = false;
        return;
      }

      this.service.getTrabajadores().pipe(
        catchError(error => {
          this.errorMessage = 'Error al obtener personal: ' + error.message;
          return of([]);
        })
      ).subscribe(personalList => {
        const personalEncontrado = personalList.find((p: any) => 
          p.nombre.toLowerCase() === this.pedido.personal.toLowerCase()
        );

        if (!personalEncontrado) {
          this.errorPersonal = true;
          this.errorMessage = 'Personal no encontrado';
          this.isLoading = false;
          return;
        }

        const pedidoFinal = {
          fecha: this.pedido.fecha,
          monto: this.pedido.monto,
          clienteId: clienteEncontrado.id,
          personalId: personalEncontrado.id
        };

        this.service.createPedido(pedidoFinal).pipe(
          catchError(error => {
            this.errorMessage = 'Error al crear el pedido: ' + error.message;
            return of(null);
          }),
          finalize(() => this.isLoading = false)
        ).subscribe(response => {
          if (response) {
            this.router.navigate(['/pedidos']);
          }
        });
      });
    });
  }

  /**
   * Crea un nuevo miembro del personal
   * Maneja los errores y redirecciona al completar
   */
  private crearPersonal() {
    this.service.createPersonal(this.personal).pipe(
      catchError(error => {
        this.errorMessage = 'Error al crear el personal: ' + error.message;
        return of(null);
      }),
      finalize(() => this.isLoading = false)
    ).subscribe(response => {
      if (response) {
        this.router.navigate(['/personal']);
      }
    });
  }

  /**
   * Método principal para crear entidades
   * Redirige a la función específica según el tipo seleccionado
   */
  crear() {
    this.errorPersonal = false;
    this.errorMessage = '';
    this.isLoading = true;

    switch (this.tipoSeleccionado) {
      case 'cliente':
        this.crearCliente();
        break;
      case 'articulo':
        this.crearArticulo();
        break;
      case 'pedido':
        this.crearPedido();
        break;
      case 'personal':
        this.crearPersonal();
        break;
      default:
        this.errorMessage = 'Tipo de entidad no válido';
        this.isLoading = false;
    }
  }
}
