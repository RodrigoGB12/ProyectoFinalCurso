package ad.GestionCatering.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Entity
@Table(name = "clientes")
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único para cada cliente", example = "1", required = true)
    private Long id;

    @Column(length = 100, nullable = false)
    @Schema(description = "Nombre completo del cliente", example = "Juan Pérez", required = true)
    private String nombre;

    @Column(length = 100, nullable = false, unique = true)
    @Email(message = "El correo debe tener un formato válido")
    @Schema(description = "Correo electrónico del cliente", example = "juan.perez@example.com", required = true)
    private String correo_electronico;

    @Column(length = 15)
    @Schema(description = "Número de teléfono del cliente", example = "+34 600 123 456")
    private String telefono;

    @Schema(description = "Dirección del cliente", example = "Calle Falsa 123, Madrid")
    private String direccion;

    @Column(length = 20, unique = true, nullable = false)
    @Schema(description = "DNI del cliente", example = "12345678A", required = true)
    private String dni;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonIgnore
    @Schema(description = "Lista de pedidos realizados por el cliente", required = true)
    private List<Pedidos> pedidos;

    public Clientes() {
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
}
