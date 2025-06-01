package ad.GestionCatering.controllers;

import ad.GestionCatering.models.Clientes;
import ad.GestionCatering.services.ClientesService;
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
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Authorization")
@RestController
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private ClientesService clientesService;

    @Operation(summary = "Obtener todos los clientes",
            description = "Devuelve una lista completa de todos los clientes registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida con éxito")
    @GetMapping
    public List<Clientes> getAllClientes() {
        return clientesService.findAll();
    }

    @Operation(summary = "Obtener datos de un cliente por ID",
            description = "Devuelve los datos de un cliente específico utilizando su ID.")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Clientes> getClienteById(@PathVariable Long id) {
        Optional<Clientes> cliente = clientesService.findById(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo cliente",
            description = "Añade un nuevo cliente al sistema y devuelve los detalles del cliente creado.")
    @ApiResponse(responseCode = "201", description = "Cliente creado con éxito")
    @PostMapping
    public ResponseEntity<Clientes> createCliente(@RequestBody @Valid Clientes cliente) {
        Clientes savedCliente = clientesService.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);
    }

    @Operation(summary = "Actualizar un cliente existente por ID",
            description = "Actualiza los datos de un cliente específico utilizando su ID.")
    @ApiResponse(responseCode = "200", description = "Cliente actualizado con éxito")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<Clientes> updateCliente(@PathVariable Long id, @RequestBody Clientes cliente) {
        Optional<Clientes> existingCliente = clientesService.findById(id);
        if (existingCliente.isPresent()) {
            cliente.setId(id);
            Clientes updatedCliente = clientesService.save(cliente);
            return ResponseEntity.ok(updatedCliente);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un cliente por ID",
            description = "Elimina un cliente específico utilizando su ID.")
    @ApiResponse(responseCode = "204", description = "Cliente eliminado con éxito")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        if (clientesService.findById(id).isPresent()) {
            clientesService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
