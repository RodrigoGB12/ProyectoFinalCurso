package ad.GestionCatering.controllers.backend;

import ad.GestionCatering.models.ArticulosMenu;
import ad.GestionCatering.repositories.ArticulosMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@Controller
@RequestMapping("/admin/menu")
public class WebMenuController {

    @Autowired
    private ArticulosMenuRepository menuRepository;

    @GetMapping
    public String listMenu(Model model) {
        model.addAttribute("articulos", menuRepository.findAll());
        if (!model.containsAttribute("articulo")) {
            model.addAttribute("articulo", new ArticulosMenu());
        }
        return "admin/menu";
    }

    @PostMapping("/save")
    public String saveMenu(@ModelAttribute ArticulosMenu menu) {
        menuRepository.save(menu);
        return "redirect:/admin/menu";
    }

    @GetMapping("/edit/{id}")
    public String editMenu(@PathVariable Long id, Model model) {
        ArticulosMenu menu = menuRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de menú inválido: " + id));
        model.addAttribute("articulo", menu);
        model.addAttribute("articulos", menuRepository.findAll());
        return "admin/menu";
    }

    @GetMapping("/delete/{id}")
    public String deleteMenu(@PathVariable Long id) {
        menuRepository.deleteById(id);
        return "redirect:/admin/menu";
    }
}
