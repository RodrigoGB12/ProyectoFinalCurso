package ad.GestionCatering.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "articulos_menu_alergenos")
public class ArticulosMenuAlergenos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único para la relación entre un artículo del menú y un alérgeno", example = "1", required = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "articulo_menu_id")
    @Schema(description = "Artículo del menú asociado a este alérgeno", required = true)
    private ArticulosMenu articuloMenu;

    @ManyToOne
    @JoinColumn(name = "alergeno_id")
    @Schema(description = "Alérgeno asociado al artículo del menú", required = true)
    private Alergenos alergeno;

    public ArticulosMenuAlergenos() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArticulosMenu getArticuloMenu() {
        return articuloMenu;
    }

    public void setArticuloMenu(ArticulosMenu articuloMenu) {
        this.articuloMenu = articuloMenu;
    }

    public Alergenos getAlergeno() {
        return alergeno;
    }

    public void setAlergeno(Alergenos alergeno) {
        this.alergeno = alergeno;
    }
}
