package ad.GestionCatering.models;

import java.util.ArrayList;
import java.util.List;

public class PlatosAlergenoDTO {
    private Long id;
    private String nombre;

    private List<String> alergenos;

    public PlatosAlergenoDTO() {
        alergenos = new ArrayList<>();
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

    public List<String> getAlergenos() {
        return alergenos;
    }

    public void setAlergenos(List<String> alergenos) {
        this.alergenos = alergenos;
    }
}
