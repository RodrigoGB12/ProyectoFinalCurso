package ad.GestionCatering.controllers;

import ad.GestionCatering.models.ArticulosMenuAlergenos;
import ad.GestionCatering.services.ArticulosMenuAlergenosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/articulos-menu-alergenos")
public class ArticulosMenuAlergenosController {

    @Autowired
    private ArticulosMenuAlergenosService articulosMenuAlergenosService;

    @Operation(summary = "Obtener todos los artículos del menú con alérgenos",
            description = "Devuelve una lista completa de todos los artículos del menú junto con sus alérgenos.")
    @ApiResponse(responseCode = "200", description = "Lista de artículos con alérgenos obtenida con éxito")
    @GetMapping
    public List<ArticulosMenuAlergenos> getAllArticulosMenuAlergenos() {
        return articulosMenuAlergenosService.findAll();
    }

    @Operation(summary = "Obtener un artículo del menú con alérgenos por ID",
            description = "Devuelve los detalles de un artículo de menú con alérgenos específico utilizando su ID.")
    @ApiResponse(responseCode = "200", description = "Artículo de menú con alérgenos encontrado")
    @ApiResponse(responseCode = "404", description = "Artículo de menú con alérgenos no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<ArticulosMenuAlergenos> getArticuloMenuAlergenosById(@PathVariable Long id) {
        Optional<ArticulosMenuAlergenos> articuloMenuAlergenos = articulosMenuAlergenosService.findById(id);
        return articuloMenuAlergenos.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo artículo del menú con alérgenos",
            description = "Añade un nuevo artículo de menú con alérgenos y devuelve los detalles del artículo creado.")
    @ApiResponse(responseCode = "201", description = "Artículo de menú con alérgenos creado con éxito")
    @PostMapping
    public ResponseEntity<ArticulosMenuAlergenos> createArticuloMenuAlergenos(@RequestBody ArticulosMenuAlergenos articuloMenuAlergenos) {
        ArticulosMenuAlergenos savedArticuloMenuAlergenos = articulosMenuAlergenosService.save(articuloMenuAlergenos);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticuloMenuAlergenos);
    }

    @Operation(summary = "Actualizar un artículo de menú con alérgenos existente por ID",
            description = "Actualiza los detalles de un artículo de menú con alérgenos específico utilizando su ID.")
    @ApiResponse(responseCode = "200", description = "Artículo de menú con alérgenos actualizado con éxito")
    @ApiResponse(responseCode = "404", description = "Artículo de menú con alérgenos no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<ArticulosMenuAlergenos> updateArticuloMenuAlergenos(@PathVariable Long id, @RequestBody ArticulosMenuAlergenos articuloMenuAlergenos) {
        Optional<ArticulosMenuAlergenos> existingArticuloMenuAlergenos = articulosMenuAlergenosService.findById(id);
        if (existingArticuloMenuAlergenos.isPresent()) {
            articuloMenuAlergenos.setId(id);
            ArticulosMenuAlergenos updatedArticuloMenuAlergenos = articulosMenuAlergenosService.save(articuloMenuAlergenos);
            return ResponseEntity.ok(updatedArticuloMenuAlergenos);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un artículo de menú con alérgenos por ID",
            description = "Elimina un artículo de menú con alérgenos específico utilizando su ID.")
    @ApiResponse(responseCode = "204", description = "Artículo de menú con alérgenos eliminado con éxito")
    @ApiResponse(responseCode = "404", description = "Artículo de menú con alérgenos no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticuloMenuAlergenos(@PathVariable Long id) {
        if (articulosMenuAlergenosService.findById(id).isPresent()) {
            articulosMenuAlergenosService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
