package ad.GestionCatering.controllers.backend;


import ad.GestionCatering.models.Alergenos;
import ad.GestionCatering.repositories.AlergenosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/alergenos")
public class WebAlergenosController {

    @Autowired
    private AlergenosRepository alergenoRepository;

    @GetMapping
    public String listAlergenos(Model model) {
        model.addAttribute("alergenos", alergenoRepository.findAll());
        if (!model.containsAttribute("alergeno")) {
            model.addAttribute("alergeno", new Alergenos());
        }
        return "admin/alergenos";
    }

    @PostMapping("/save")
    public String saveAlergeno(@ModelAttribute Alergenos alergeno) {
        alergenoRepository.save(alergeno);
        return "redirect:/admin/alergenos";
    }


    @GetMapping("/edit/{id}")
    public String editAlergeno(@PathVariable Long id, Model model) {
        Alergenos alergeno = alergenoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de alérgeno inválido: " + id));
        model.addAttribute("alergeno", alergeno);
        model.addAttribute("alergenos", alergenoRepository.findAll());
        return "admin/alergenos";
    }

    @GetMapping("/delete/{id}")
    public String deleteAlergeno(@PathVariable Long id) {
        alergenoRepository.deleteById(id);
        return "redirect:/admin/alergenos";
    }
}

