package ad.GestionCatering.controllers;

import ad.GestionCatering.models.Alergenos;
import ad.GestionCatering.services.AlergenosService;
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
@RequestMapping("/alergenos")
public class AlergenosController {

    @Autowired
    private AlergenosService alergenosService;

    @Operation(summary = "Obtener todos los alérgenos", description = "Devuelve una lista completa de todos los alérgenos disponibles.")
    @ApiResponse(responseCode = "200", description = "Lista de alérgenos obtenida con éxito")
    @GetMapping
    public List<Alergenos> obtenerTodas() {
        return alergenosService.findAll();
    }

    @Operation(summary = "Obtener un alérgeno por ID", description = "Devuelve los detalles de un alérgeno específico utilizando su ID.")
    @ApiResponse(responseCode = "200", description = "Alérgeno encontrado")
    @ApiResponse(responseCode = "404", description = "Alérgeno no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Alergenos> getAlergenoById(@PathVariable Long id) {
        Optional<Alergenos> alergeno = alergenosService.findById(id);
        return alergeno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo alérgeno", description = "Añade un nuevo alérgeno a la base de datos.")
    @ApiResponse(responseCode = "201", description = "Alérgeno creado con éxito")
    @PostMapping
    public ResponseEntity<Alergenos> createAlergeno(@RequestBody Alergenos alergeno) {
        Alergenos savedAlergeno = alergenosService.save(alergeno);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAlergeno);
    }

    @Operation(summary = "Actualizar un alérgeno por ID", description = "Actualiza los detalles de un alérgeno existente utilizando su ID.")
    @ApiResponse(responseCode = "200", description = "Alérgeno actualizado con éxito")
    @ApiResponse(responseCode = "404", description = "Alérgeno no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<Alergenos> updateAlergeno(@PathVariable Long id, @RequestBody Alergenos alergeno) {
        Optional<Alergenos> existingAlergeno = alergenosService.findById(id);
        if (existingAlergeno.isPresent()) {
            alergeno.setId(id);
            Alergenos updatedAlergeno = alergenosService.save(alergeno);
            return ResponseEntity.ok(updatedAlergeno);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un alérgeno por ID", description = "Elimina un alérgeno específico utilizando su ID.")
    @ApiResponse(responseCode = "204", description = "Alérgeno eliminado con éxito")
    @ApiResponse(responseCode = "404", description = "Alérgeno no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlergeno(@PathVariable Long id) {
        if (alergenosService.findById(id).isPresent()) {
            alergenosService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
