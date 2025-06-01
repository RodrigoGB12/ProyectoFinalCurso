package ad.GestionCatering.controllers.backend;

import ad.GestionCatering.models.ArticulosMenu;
import ad.GestionCatering.models.ArticulosMenuAlergenos;
import ad.GestionCatering.repositories.ArticulosMenuAlergenosRepository;
import ad.GestionCatering.repositories.ArticulosMenuRepository;
import ad.GestionCatering.repositories.AlergenosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/articulosMenuAlergenos")
public class WebArticulosMenuAlergenosController {

    @Autowired
    private ArticulosMenuAlergenosRepository menuAlergenosRepository;

    @Autowired
    private ArticulosMenuRepository menuRepository;

    @Autowired
    private AlergenosRepository alergenoRepository;

    // Mostrar lista de relaciones Artículos-Alergenos y el formulario
    @GetMapping
    public String listArticulosMenuAlergenos(Model model) {
        model.addAttribute("relaciones", menuAlergenosRepository.findAll());
        model.addAttribute("articulos", menuRepository.findAll());
        model.addAttribute("alergenos", alergenoRepository.findAll());
        model.addAttribute("relacion", new ArticulosMenuAlergenos()); // Para una relación nueva
        return "admin/articulosMenuAlergenos"; // Página con tabla y formulario
    }

    // Mostrar formulario de edición de una relación Artículo-Alergeno
    @GetMapping("/edit/{id}")
    public String editRelacion(@PathVariable Long id, Model model) {
        ArticulosMenuAlergenos articulosMenuAlergenos = menuAlergenosRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de relación inválido: " + id));
        model.addAttribute("relacion", articulosMenuAlergenos); // Relación a editar
        model.addAttribute("articulos", menuRepository.findAll());
        model.addAttribute("alergenos", alergenoRepository.findAll());
        return "admin/articulosMenuAlergenos"; // Página con tabla y formulario
    }

    // Guardar la relación Artículo-Alergeno
    @PostMapping("/save")
    public String saveRelacion(@ModelAttribute ArticulosMenuAlergenos relacion) {
        menuAlergenosRepository.save(relacion);
        return "redirect:/admin/articulosMenuAlergenos"; // Redirigir a la página con tabla y formulario
    }

    // Eliminar una relación Artículo-Alergeno
    @GetMapping("/delete/{id}")
    public String deleteRelacion(@PathVariable Long id) {
        menuAlergenosRepository.deleteById(id);
        return "redirect:/admin/articulosMenuAlergenos"; // Redirigir a la página con tabla y formulario
    }
}
