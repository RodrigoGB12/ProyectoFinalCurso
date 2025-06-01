package ad.GestionCatering.controllers.backend;

import ad.GestionCatering.models.Personal;
import ad.GestionCatering.repositories.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/personal")
public class WebPersonalController {

    @Autowired
    private PersonalRepository personalRepository;

    @GetMapping
    public String listPersonal(Model model) {
        model.addAttribute("personalList", personalRepository.findAll());
        if (!model.containsAttribute("personal")) {
            model.addAttribute("personal", new Personal());
        }
        return "admin/personal";
    }

    @PostMapping("/save")
    public String savePersonal(@ModelAttribute Personal personal) {
        personalRepository.save(personal);
        return "redirect:/admin/personal";
    }

    @GetMapping("/edit/{id}")
    public String editPersonal(@PathVariable Long id, Model model) {
        Personal personal = personalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de personal inválido: " + id));
        model.addAttribute("personal", personal);
        model.addAttribute("personalList", personalRepository.findAll());
        return "admin/personal";
    }

    @GetMapping("/delete/{id}")
    public String deletePersonal(@PathVariable Long id) {
        personalRepository.deleteById(id);
        return "redirect:/admin/personal";
    }
}
