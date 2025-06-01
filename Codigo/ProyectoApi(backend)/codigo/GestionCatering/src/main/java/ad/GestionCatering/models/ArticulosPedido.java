package ad.GestionCatering.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "articulos_pedido")
public class ArticulosPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único para cada artículo del pedido", example = "1", required = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @Schema(description = "Pedido al que pertenece este artículo", required = true)
    private Pedidos pedido;

    @ManyToOne
    @JoinColumn(name = "articulo_menu_id")
    @Schema(description = "Artículo del menú asociado a este pedido", required = true)
    private ArticulosMenu articuloMenu;

    @Column(nullable = false)
    @Schema(description = "Cantidad de unidades de este artículo en el pedido", example = "2", required = true)
    private int cantidad;

    @Schema(description = "Precio del artículo en este pedido", example = "15.99")
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "personal_id")
    @Schema(description = "Personal que gestionó este artículo del pedido", required = true)
    private Personal personal;

    public ArticulosPedido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedidos getPedido() {
        return pedido;
    }

    public void setPedido(Pedidos pedido) {
        this.pedido = pedido;
    }

    public ArticulosMenu getArticuloMenu() {
        return articuloMenu;
    }

    public void setArticuloMenu(ArticulosMenu articuloMenu) {
        this.articuloMenu = articuloMenu;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
}
