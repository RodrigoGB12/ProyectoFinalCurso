/**
 * Componente para mostrar los detalles de un plato específico
 * Muestra la información del plato y sus alérgenos asociados
 */
import { Component } from '@angular/core';
import { Input } from '@angular/core';
import { CateringServiceService } from '../service/catering-service.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-plato',
  standalone: false,
  templateUrl: './plato.component.html',
  styleUrl: './plato.component.css'
})
export class PlatoComponent {
  /** Datos de las relaciones entre menú y alérgenos */
  menuData: any[] = [];
  /** ID del plato actual */
  id: any;
  /** Lista de alérgenos asociados al plato */
  alergenos: any[] = [];
  /** Datos del plato actual */
  plato: any;

  /**
   * Constructor del componente
   * @param service Servicio para interactuar con la API
   * @param activatedRoute Servicio para obtener parámetros de la ruta
   */
  constructor(
    private service: CateringServiceService,
    private activatedRoute: ActivatedRoute
  ) {
    this.activatedRoute.params.subscribe(params => {
      this.id = params['id'];
    });
  }

  /**
   * Método del ciclo de vida que se ejecuta al inicializar el componente
   * Carga los datos del plato y sus alérgenos asociados
   */
  ngOnInit() {
    // Obtener los datos del plato
    this.plato = this.service.getPlato(this.id).subscribe({
      next: (data) => {
        this.plato = data;
        console.log(this.plato);
      },
      error: (e) => { console.error(e) },
      complete: () => { console.info('complete'); }
    });

    console.log('Componente Inicializado');

    // Obtener las relaciones entre platos y alérgenos
    this.service.getRelacionesAlerPlat().subscribe(
      (data: any[]) => {
        this.menuData = data;
        this.alergenos = this.service.getAlergenosPorPlato(data, this.plato.nombre);
        console.log(this.alergenos);
      },
      error => {
        console.error('Error al obtener los datos:', error);
      }
    );
  }
}


