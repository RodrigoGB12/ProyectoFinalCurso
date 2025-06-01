package ad.GestionCatering.controllers;

import ad.GestionCatering.models.ArticulosPedido;
import ad.GestionCatering.models.PedidoCompletoDTO;
import ad.GestionCatering.models.Pedidos;
import ad.GestionCatering.services.PedidosService;
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
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    private PedidosService pedidosService;

    @Operation(summary = "Obtener todos los pedidos",
            description = "Devuelve una lista completa de todos los pedidos realizados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de pedidos obtenida con éxito")
    @GetMapping
    public List<Pedidos> getAllPedidos() {
        return pedidosService.findAll();
    }

    @Operation(summary = "Obtener los detalles completos de un pedido por ID",
            description = "Devuelve los detalles de un pedido específico, incluyendo artículos, cliente, personal, etc.")
    @ApiResponse(responseCode = "200", description = "Detalles del pedido obtenidos con éxito")
    @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    @GetMapping("/detalles/{id}")
    public PedidoCompletoDTO getDetallePedido(@PathVariable Long id) {
        Pedidos pedido = pedidosService.findById(id).orElse(new Pedidos());
        if (pedido.getEstado() == null){
            return null;
        } else {
            PedidoCompletoDTO pedidoCompletoDTO = new PedidoCompletoDTO();
            pedidoCompletoDTO.setId(pedido.getId());
            pedidoCompletoDTO.setFecha_pedido(pedido.getFecha_pedido());
            pedidoCompletoDTO.setEstado(pedido.getEstado());
            pedidoCompletoDTO.setNombre(pedido.getCliente().getNombre());
            pedidoCompletoDTO.setDni(pedido.getCliente().getDni());
            pedidoCompletoDTO.setArticulosPedido(pedido.getArticulosPedido());
            pedidoCompletoDTO.setNombrePersonal(pedido.getPersonal().getNombre());

            double total = 0;
            for (ArticulosPedido articulosPedido : pedidoCompletoDTO.getArticulosPedido()){
                total = total + articulosPedido.getArticuloMenu().getPrecio() * articulosPedido.getCantidad();
            }
            pedidoCompletoDTO.setMonto_total(total);
            return pedidoCompletoDTO;
        }
    }

    @Operation(summary = "Obtener un pedido por ID",
            description = "Devuelve los detalles de un pedido específico usando su ID.")
    @ApiResponse(responseCode = "200", description = "Pedido encontrado")
    @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Pedidos> getPedidoById(@PathVariable Long id) {
        Optional<Pedidos> pedido = pedidosService.findById(id);
        return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo pedido",
            description = "Añade un nuevo pedido al sistema y devuelve los detalles del pedido creado.")
    @ApiResponse(responseCode = "201", description = "Pedido creado con éxito")
    @PostMapping
    public ResponseEntity<Pedidos> createPedido(@RequestBody Pedidos pedido) {
        Pedidos savedPedido = pedidosService.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPedido);
    }

    @Operation(summary = "Actualizar un pedido existente por ID",
            description = "Actualiza los detalles de un pedido existente utilizando su ID.")
    @ApiResponse(responseCode = "200", description = "Pedido actualizado con éxito")
    @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<Pedidos> updatePedido(@PathVariable Long id, @RequestBody Pedidos pedido) {
        Optional<Pedidos> existingPedido = pedidosService.findById(id);
        if (existingPedido.isPresent()) {
            pedido.setId(id);
            Pedidos updatedPedido = pedidosService.save(pedido);
            return ResponseEntity.ok(updatedPedido);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un pedido por ID",
            description = "Elimina un pedido específico usando su ID.")
    @ApiResponse(responseCode = "204", description = "Pedido eliminado con éxito")
    @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        if (pedidosService.findById(id).isPresent()) {
            pedidosService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
