package ad.GestionCatering.services;

import ad.GestionCatering.models.ArticulosMenu;
import ad.GestionCatering.repositories.ArticulosMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticulosMenuService {

    @Autowired
    private ArticulosMenuRepository articulosMenuRepository;

    public List<ArticulosMenu> findAll() {
        return articulosMenuRepository.findAll();
    }

    public Optional<ArticulosMenu> findById(Long id) {
        return articulosMenuRepository.findById(id);
    }

    public ArticulosMenu save(ArticulosMenu articuloMenu) {
        return articulosMenuRepository.save(articuloMenu);
    }

    public void delete(Long id) {
        articulosMenuRepository.deleteById(id);
    }

}
