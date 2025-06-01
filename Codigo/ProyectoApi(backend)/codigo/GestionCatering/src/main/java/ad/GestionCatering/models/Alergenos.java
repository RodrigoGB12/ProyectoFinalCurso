package ad.GestionCatering.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Entity
@Table(name = "alergenos")
public class Alergenos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del alérgeno", example = "1", required = true)
    private Long id;

    @Column(length = 100, nullable = false)
    @Schema(description = "Nombre del alérgeno", example = "Gluten", required = true)
    private String nombre;

    @Schema(description = "Descripción del alérgeno", example = "Contiene trigo y sus derivados", required = false)
    private String descripcion;

    @OneToMany(mappedBy = "alergeno", cascade = CascadeType.ALL)
    @JsonIgnore
    @Schema(description = "Lista de artículos del menú relacionados con este alérgeno", required = false)
    private List<ArticulosMenuAlergenos> articulosMenuAlergenos;

    public Alergenos() {
    }

    public Long getId() {
        return id;
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

    public List<ArticulosMenuAlergenos> getArticulosMenuAlergenos() {
        return articulosMenuAlergenos;
    }

    public void setArticulosMenuAlergenos(List<ArticulosMenuAlergenos> articulosMenuAlergenos) {
        this.articulosMenuAlergenos = articulosMenuAlergenos;
    }
}
