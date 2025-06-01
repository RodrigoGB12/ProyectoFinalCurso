package ad.GestionCatering.controllers.backend;

import ad.GestionCatering.models.Clientes;
import ad.GestionCatering.models.Pedidos;
import ad.GestionCatering.models.Personal;
import ad.GestionCatering.models.Rol;
import ad.GestionCatering.repositories.ClientesRepository;
import ad.GestionCatering.repositories.PedidosRepository;
import ad.GestionCatering.repositories.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/pedidos")
public class WebPedidosController {

    @Autowired
    private PedidosRepository pedidoRepository;

    @Autowired
    private ClientesRepository clienteRepository;
    @Autowired
    private PersonalRepository personalRepository;

    // Muestra el listado de pedidos y el formulario (para crear o editar)
    @GetMapping
    public String listPedidos(Model model) {
        model.addAttribute("pedidos", pedidoRepository.findAll());
        if (!model.containsAttribute("pedido")) {
            model.addAttribute("pedido", new Pedidos());
        }
        // Se añade la lista de clientes para el combo
        model.addAttribute("clientes", clienteRepository.findAll());

        List<Personal> listaPersonalTodos = personalRepository.findAll();
        List<Personal> listaPersonal = new ArrayList<>();

        for (Personal personal:listaPersonalTodos){
            if (personal.getRol().name().equals("Conductor")){
                listaPersonal.add(personal);
            }
        }
        model.addAttribute("personals",listaPersonal);
        return "admin/pedidos";
    }

    // Guarda (crea o actualiza) un pedido
    @PostMapping("/save")
    public String savePedido(@ModelAttribute Pedidos pedido,
                             @RequestParam("clienteId") Long clienteId,
                             @RequestParam("personalId") Long personalId) {
        // Se carga el cliente usando el ID enviado desde el formulario
        Clientes cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado para el ID: " + clienteId));
        Personal personal = personalRepository.findById(personalId)
                .orElseThrow(() -> new IllegalArgumentException("PE no encontrado para el ID: " + personalId));
        pedido.setPersonal(personal);
        pedido.setCliente(cliente);
        pedidoRepository.save(pedido);
        return "redirect:/admin/pedidos";
    }

    // Edita un pedido: se carga el objeto y se muestra en la misma vista
    @GetMapping("/edit/{id}")
    public String editPedido(@PathVariable Long id, Model model) {
        Pedidos pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de pedido inválido: " + id));
        model.addAttribute("pedido", pedido);
        model.addAttribute("pedidos", pedidoRepository.findAll());
        model.addAttribute("clientes", clienteRepository.findAll());

        List<Personal> listaPersonalTodos = personalRepository.findAll();
        List<Personal> listaPersonal = new ArrayList<>();

        for (Personal personal:listaPersonalTodos){
            if (personal.getRol().name().equals("Conductor")){
                listaPersonal.add(personal);
            }
        }
        model.addAttribute("personals",listaPersonal);

        return "admin/pedidos";
    }

    // Elimina un pedido y redirige al listado
    @GetMapping("/delete/{id}")
    public String deletePedido(@PathVariable Long id) {
        pedidoRepository.deleteById(id);
        return "redirect:/admin/pedidos";
    }
}
