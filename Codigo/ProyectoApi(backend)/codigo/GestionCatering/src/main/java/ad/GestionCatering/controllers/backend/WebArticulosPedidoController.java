package ad.GestionCatering.controllers.backend;

import ad.GestionCatering.models.ArticulosPedido;
import ad.GestionCatering.models.Personal;
import ad.GestionCatering.models.Rol;
import ad.GestionCatering.repositories.ArticulosMenuRepository;
import ad.GestionCatering.repositories.ArticulosPedidoRepository;
import ad.GestionCatering.repositories.PedidosRepository;
import ad.GestionCatering.repositories.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
    @RequestMapping("/admin/articulosPedido")
    public class WebArticulosPedidoController {

        @Autowired
        private ArticulosPedidoRepository articulosPedidoRepository;

        @Autowired
        private PedidosRepository pedidosRepository;

        @Autowired
        private ArticulosMenuRepository articulosMenuRepository;

        @Autowired
        private PersonalRepository personalRepository;

        // Mostrar lista de relaciones Artículos - Pedidos
        @GetMapping
        public String listArticulosPedido(Model model) {
            model.addAttribute("articulosPedidos", articulosPedidoRepository.findAll());
            model.addAttribute("pedidos", pedidosRepository.findAll());
            model.addAttribute("articulosMenu", articulosMenuRepository.findAll());

            List<Personal> listaPersonalTodos = personalRepository.findAll();
            List<Personal> listaPersonal = new ArrayList<>();

            for (Personal personal:listaPersonalTodos){
                if (personal.getRol().name().equals("Cocinero")){
                    listaPersonal.add(personal);
                }
            }
            model.addAttribute("personales",listaPersonal);

            model.addAttribute("articuloPedido", new ArticulosPedido()); // Para una nueva relación
            return "admin/articulosPedido"; // Página con tabla y formulario
        }

        // Mostrar formulario de edición de una relación Artículo - Pedido
        @GetMapping("/edit/{id}")
        public String editArticuloPedido(@PathVariable Long id, Model model) {
            ArticulosPedido articuloPedido = articulosPedidoRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("ID de Artículos Pedido inválido: " + id));
            model.addAttribute("articuloPedido", articuloPedido); // Relación a editar
            model.addAttribute("pedidos", pedidosRepository.findAll());
            model.addAttribute("articulosMenu", articulosMenuRepository.findAll());
            List<Personal> listaPersonalTodos = personalRepository.findAll();
            List<Personal> listaPersonal = new ArrayList<>();

            for (Personal personal:listaPersonalTodos){
                if (personal.getRol().name().equals("Cocinero")){
                    listaPersonal.add(personal);
                }
            }
            model.addAttribute("personales",listaPersonal);
            return "admin/articulosPedido"; // Página con tabla y formulario
        }

        // Guardar la relación Artículo - Pedido
        @PostMapping("/save")
        public String saveArticuloPedido(@ModelAttribute ArticulosPedido articuloPedido) {
            articuloPedido.setPrecio(articuloPedido.getArticuloMenu().getPrecio()*articuloPedido.getCantidad());
            articulosPedidoRepository.save(articuloPedido);
            return "redirect:/admin/articulosPedido"; // Redirigir a la página con tabla y formulario
        }

        // Eliminar una relación Artículo - Pedido
        @GetMapping("/delete/{id}")
        public String deleteArticuloPedido(@PathVariable Long id) {
            articulosPedidoRepository.deleteById(id);
            return "redirect:/admin/articulosPedido"; // Redirigir a la página con tabla y formulario
        }
    }

