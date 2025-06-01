package ad.GestionCatering.controllers;

import ad.GestionCatering.models.ArticulosPedido;
import ad.GestionCatering.services.ArticulosPedidoService;
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
@RequestMapping("/articulos-pedido")
public class ArticulosPedidoController {

    @Autowired
    private ArticulosPedidoService articulosPedidoService;

    @Operation(summary = "Obtener todos los artículos de un pedido",
            description = "Devuelve una lista completa de todos los artículos de pedidos registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de artículos de pedido obtenida con éxito")
    @GetMapping
    public List<ArticulosPedido> getAllArticulosPedido() {
        return articulosPedidoService.findAll();
    }

    @Operation(summary = "Obtener un artículo de pedido por ID",
            description = "Devuelve los detalles de un artículo de pedido específico utilizando su ID.")
    @ApiResponse(responseCode = "200", description = "Artículo de pedido encontrado")
    @ApiResponse(responseCode = "404", description = "Artículo de pedido no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<ArticulosPedido> getArticuloPedidoById(@PathVariable Long id) {
        Optional<ArticulosPedido> articuloPedido = articulosPedidoService.findById(id);
        return articuloPedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo artículo de pedido",
            description = "Añade un nuevo artículo a un pedido y devuelve los detalles del artículo creado.")
    @ApiResponse(responseCode = "201", description = "Artículo de pedido creado con éxito")
    @PostMapping
    public ResponseEntity<ArticulosPedido> createArticuloPedido(@RequestBody ArticulosPedido articuloPedido) {
        ArticulosPedido savedArticuloPedido = articulosPedidoService.save(articuloPedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticuloPedido);
    }

    @Operation(summary = "Actualizar un artículo de pedido existente por ID",
            description = "Actualiza los detalles de un artículo de pedido específico utilizando su ID.")
    @ApiResponse(responseCode = "200", description = "Artículo de pedido actualizado con éxito")
    @ApiResponse(responseCode = "404", description = "Artículo de pedido no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<ArticulosPedido> updateArticuloPedido(@PathVariable Long id, @RequestBody ArticulosPedido articuloPedido) {
        Optional<ArticulosPedido> existingArticuloPedido = articulosPedidoService.findById(id);
        if (existingArticuloPedido.isPresent()) {
            articuloPedido.setId(id);
            ArticulosPedido updatedArticuloPedido = articulosPedidoService.save(articuloPedido);
            return ResponseEntity.ok(updatedArticuloPedido);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un artículo de pedido por ID",
            description = "Elimina un artículo de pedido específico utilizando su ID.")
    @ApiResponse(responseCode = "204", description = "Artículo de pedido eliminado con éxito")
    @ApiResponse(responseCode = "404", description = "Artículo de pedido no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticuloPedido(@PathVariable Long id) {
        if (articulosPedidoService.findById(id).isPresent()) {
            articulosPedidoService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
