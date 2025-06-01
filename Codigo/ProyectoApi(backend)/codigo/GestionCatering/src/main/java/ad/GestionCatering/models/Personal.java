package ad.GestionCatering.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Entity
@Table(name = "personal")
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único para cada personal", example = "1", required = true)
    private Long id;

    @Column(length = 100, nullable = false)
    @Schema(description = "Nombre del personal", example = "Juan Pérez", required = true)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Rol del personal (Ejemplo: ADMIN, COCINERO, MESERO)", example = "COCINERO", required = true)
    private Rol rol;

    @Column(length = 100, nullable = false, unique = true)
    @Email(message = "El correo debe tener un formato válido")
    @Schema(description = "Correo electrónico del personal", example = "juan.perez@ejemplo.com", required = true)
    private String correo_electronico;

    @Column(length = 15)
    @Schema(description = "Teléfono del personal", example = "+34 612 345 678")
    private String telefono;

    @Column(length = 20, nullable = false, unique = true)
    @Schema(description = "DNI del personal", example = "12345678A", required = true)
    private String dni;

    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL)
    @JsonIgnore
    @Schema(description = "Lista de pedidos realizados por el personal")
    private List<Pedidos> pedidos;

    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL)
    @JsonIgnore
    @Schema(description = "Lista de artículos asociados al personal en los pedidos")
    private List<ArticulosPedido> articulosPedidos;

    public Personal() {
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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public List<Pedidos> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedidos> pedidos) {
        this.pedidos = pedidos;
    }

    public List<ArticulosPedido> getArticulosPedidos() {
        return articulosPedidos;
    }

    public void setArticulosPedidos(List<ArticulosPedido> articulosPedidos) {
        this.articulosPedidos = articulosPedidos;
    }
}
