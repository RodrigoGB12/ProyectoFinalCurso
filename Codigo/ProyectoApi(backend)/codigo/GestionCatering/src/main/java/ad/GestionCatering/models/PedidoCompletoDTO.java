package ad.GestionCatering.models;

import java.sql.Date;
import java.util.List;

public class PedidoCompletoDTO {
    private Long id;
    private Date fecha_pedido;
    private String estado;

    private String nombre;
    private String dni;

    private List<ArticulosPedido> articulosPedido;

    private String nombrePersonal;
    private Double monto_total;

    public PedidoCompletoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public List<ArticulosPedido> getArticulosPedido() {
        return articulosPedido;
    }

    public void setArticulosPedido(List<ArticulosPedido> articulosPedido) {
        this.articulosPedido = articulosPedido;
    }

    public String getNombrePersonal() {
        return nombrePersonal;
    }

    public void setNombrePersonal(String nombrePersonal) {
        this.nombrePersonal = nombrePersonal;
    }

    public Double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(Double monto_total) {
        this.monto_total = monto_total;
    }
}
