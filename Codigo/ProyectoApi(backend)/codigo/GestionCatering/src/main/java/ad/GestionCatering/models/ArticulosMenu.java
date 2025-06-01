package ad.GestionCatering.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Entity
@Table(name = "articulos_menu")
public class ArticulosMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del artículo del menú", example = "1", required = true)
    private Long id;

    @Column(length = 100, nullable = false)
    @Schema(description = "Nombre del artículo del menú", example = "Pizza Margherita", required = true)
    private String nombre;

    @Schema(description = "Descripción del artículo del menú", example = "Pizza con salsa de tomate, mozzarella y albahaca", required = false)
    private String descripcion;

    @Column(nullable = false)
    @Schema(description = "Precio del artículo del menú", example = "8.99", required = true)
    private Double precio;

    @Schema(description = "URL de la imagen del artículo del menú", example = "http://example.com/pizza.jpg", required = false)
    private String imagen;

    @OneToMany(mappedBy = "articuloMenu", cascade = CascadeType.ALL)
    @JsonIgnore
    @Schema(description = "Lista de los alérgenos asociados con este artículo del menú", required = false)
    private List<ArticulosMenuAlergenos> articulosMenuAlergenos;

    public ArticulosMenu() {
    }

    public Long getId() {
        return id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public List<ArticulosMenuAlergenos> getArticulosMenuAlergenos() {
        return articulosMenuAlergenos;
    }

    public void setArticulosMenuAlergenos(List<ArticulosMenuAlergenos> articulosMenuAlergenos) {
        this.articulosMenuAlergenos = articulosMenuAlergenos;
    }
}
