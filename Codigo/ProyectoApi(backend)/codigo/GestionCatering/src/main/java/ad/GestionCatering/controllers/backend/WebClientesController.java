package ad.GestionCatering.controllers.backend;

import ad.GestionCatering.models.Clientes;
import ad.GestionCatering.repositories.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/clientes")
public class WebClientesController {

    @Autowired
    private ClientesRepository clienteRepository;

    @GetMapping
    public String listClientes(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        if (!model.containsAttribute("cliente")) {
            model.addAttribute("cliente", new Clientes());
        }
        return "admin/clientes";
    }

    @PostMapping("/save")
    public String saveCliente(@ModelAttribute Clientes cliente) {
        clienteRepository.save(cliente);
        return "redirect:/admin/clientes";
    }

    @GetMapping("/edit/{id}")
    public String editCliente(@PathVariable Long id, Model model) {
        Clientes cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de cliente inválido: " + id));
        model.addAttribute("cliente", cliente);
        model.addAttribute("clientes", clienteRepository.findAll());
        return "admin/clientes";
    }

    @GetMapping("/delete/{id}")
    public String deleteCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return "redirect:/admin/clientes";
    }
}
