package ad.GestionCatering.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.sql.Date;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "pedidos")
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único para cada pedido", example = "1", required = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @Schema(description = "Cliente que realizó el pedido", required = true)
    private Clientes cliente;

    @Schema(description = "Fecha en que se realizó el pedido", example = "2025-02-18", required = true)
    private Date fecha_pedido;

    @Column(nullable = false)
    @Schema(description = "Estado del pedido (por ejemplo: 'pendiente', 'entregado')", example = "pendiente", required = true)
    private String estado;

    @Column(nullable = false)
    @Schema(description = "Monto total del pedido", example = "150.50", required = true)
    private Double monto_total;

    @ManyToOne
    @JoinColumn(name = "personal_id", nullable = false)
    @Schema(description = "Personal que gestionó el pedido", required = true)
    private Personal personal;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @JsonIgnore
    @Schema(description = "Lista de artículos del pedido", required = true)
    private List<ArticulosPedido> articulosPedido;

    public Pedidos() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Date getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(Date fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(Double monto_total) {
        this.monto_total = monto_total;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public List<ArticulosPedido> getArticulosPedido() {
        return articulosPedido;
    }

    public void setArticulosPedido(List<ArticulosPedido> articulosPedido) {
        this.articulosPedido = articulosPedido;
    }
}
