package ad.GestionCatering.controllers;

import ad.GestionCatering.models.Personal;
import ad.GestionCatering.services.PersonalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personal")
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    @Operation(summary = "Obtener todos los registros de personal",
            description = "Devuelve una lista completa de todos los registros de personal disponibles en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de personal obtenida con éxito")
    @GetMapping
    public List<Personal> getAllPersonal() {
        return personalService.findAll();
    }

    @Operation(summary = "Obtener datos del personal por ID",
            description = "Devuelve los datos de un miembro de personal específico usando su ID.")
    @ApiResponse(responseCode = "200", description = "Personal encontrado")
    @ApiResponse(responseCode = "404", description = "Personal no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Personal> getPersonalById(@PathVariable Long id) {
        Optional<Personal> personal = personalService.findById(id);
        return personal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo registro de personal",
            description = "Añade un nuevo miembro al personal y devuelve los detalles del nuevo registro.")
    @ApiResponse(responseCode = "201", description = "Personal creado con éxito")
    @PostMapping
    public ResponseEntity<Personal> createPersonal(@RequestBody @Valid Personal personal) {
        Personal savedPersonal = personalService.save(personal);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPersonal);
    }

    @Operation(summary = "Actualizar un registro de personal por ID",
            description = "Actualiza los datos de un miembro del personal específico usando su ID.")
    @ApiResponse(responseCode = "200", description = "Personal actualizado con éxito")
    @ApiResponse(responseCode = "404", description = "Personal no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<Personal> updatePersonal(@PathVariable Long id, @RequestBody Personal personal) {
        Optional<Personal> existingPersonal = personalService.findById(id);
        if (existingPersonal.isPresent()) {
            personal.setId(id);
            Personal updatedPersonal = personalService.save(personal);
            return ResponseEntity.ok(updatedPersonal);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un registro de personal",
            description = "Elimina el registro de un miembro del personal específico usando su ID.")
    @ApiResponse(responseCode = "204", description = "Personal eliminado con éxito")
    @ApiResponse(responseCode = "404", description = "Personal no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonal(@PathVariable Long id) {
        if (personalService.findById(id).isPresent()) {
            personalService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
