package ad.GestionCatering.controllers;

import ad.GestionCatering.models.ArticulosMenu;
import ad.GestionCatering.models.ArticulosMenuAlergenos;
import ad.GestionCatering.models.PlatosAlergenoDTO;
import ad.GestionCatering.services.ArticulosMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/menu")
public class ArticulosMenuController {

    @Autowired
    private ArticulosMenuService articulosMenuService;

    @Operation(summary = "Obtener todos los artículos del menú",
            description = "Devuelve una lista completa de todos los artículos del menú disponibles en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de artículos del menú obtenida con éxito")
    @GetMapping
    public List<ArticulosMenu> getAllArticulosMenu() {
        return articulosMenuService.findAll();
    }

    @Operation(summary = "Obtener un artículo del menú por ID",
            description = "Devuelve los detalles de un artículo de menú específico utilizando su ID.")
    @ApiResponse(responseCode = "200", description = "Artículo de menú encontrado")
    @ApiResponse(responseCode = "404", description = "Artículo de menú no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<ArticulosMenu> getArticuloMenuById(@PathVariable Long id) {
        Optional<ArticulosMenu> articuloMenu = articulosMenuService.findById(id);
        return articuloMenu.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener platos y sus alérgenos",
            description = "Devuelve una lista de platos junto con los alérgenos asociados a cada uno de ellos.")
    @ApiResponse(responseCode = "200", description = "Lista de platos con alérgenos obtenida con éxito")
    @GetMapping("/detalleAlergenos")
    public List<PlatosAlergenoDTO> getPlatosAlergeno() {
        List<ArticulosMenu> articulosMenus = articulosMenuService.findAll();

        List<PlatosAlergenoDTO> platosAlergenoDTO = new ArrayList<>();

        for (ArticulosMenu articulosMenu: articulosMenus){
            PlatosAlergenoDTO platosAlergenoDTO1 = new PlatosAlergenoDTO();
            platosAlergenoDTO1.setId(articulosMenu.getId());
            platosAlergenoDTO1.setNombre(articulosMenu.getNombre());
            for (ArticulosMenuAlergenos articulosMenuAlergenos: articulosMenu.getArticulosMenuAlergenos()){
                platosAlergenoDTO1.getAlergenos().add(articulosMenuAlergenos.getAlergeno().getNombre());
            }
            platosAlergenoDTO.add(platosAlergenoDTO1);
        }
        return platosAlergenoDTO;
    }

    @Operation(summary = "Crear un nuevo artículo de menú",
            description = "Añade un nuevo artículo al menú y devuelve los detalles del artículo creado.")
    @ApiResponse(responseCode = "201", description = "Artículo del menú creado con éxito")
    @PostMapping
    public ResponseEntity<ArticulosMenu> createArticuloMenu(@RequestBody ArticulosMenu articuloMenu) {
        ArticulosMenu savedArticuloMenu = articulosMenuService.save(articuloMenu);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticuloMenu);
    }

    @Operation(summary = "Actualizar un artículo de menú existente por ID",
            description = "Actualiza los detalles de un artículo de menú específico utilizando su ID.")
    @ApiResponse(responseCode = "200", description = "Artículo del menú actualizado con éxito")
    @ApiResponse(responseCode = "404", description = "Artículo de menú no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<ArticulosMenu> updateArticuloMenu(@PathVariable Long id, @RequestBody ArticulosMenu articuloMenu) {
        Optional<ArticulosMenu> existingArticuloMenu = articulosMenuService.findById(id);
        if (existingArticuloMenu.isPresent()) {
            articuloMenu.setId(id);
            ArticulosMenu updatedArticuloMenu = articulosMenuService.save(articuloMenu);
            return ResponseEntity.ok(updatedArticuloMenu);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un artículo de menú por ID",
            description = "Elimina un artículo de menú específico utilizando su ID.")
    @ApiResponse(responseCode = "204", description = "Artículo del menú eliminado con éxito")
    @ApiResponse(responseCode = "404", description = "Artículo de menú no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticuloMenu(@PathVariable Long id) {
        if (articulosMenuService.findById(id).isPresent()) {
            articulosMenuService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
