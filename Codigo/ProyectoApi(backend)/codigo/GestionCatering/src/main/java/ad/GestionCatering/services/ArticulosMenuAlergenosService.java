package ad.GestionCatering.services;

import ad.GestionCatering.models.ArticulosMenuAlergenos;
import ad.GestionCatering.repositories.ArticulosMenuAlergenosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticulosMenuAlergenosService {

    @Autowired
    private ArticulosMenuAlergenosRepository articulosMenuAlergenosRepository;

    public List<ArticulosMenuAlergenos> findAll() {
        return articulosMenuAlergenosRepository.findAll();
    }

    public Optional<ArticulosMenuAlergenos> findById(Long id) {
        return articulosMenuAlergenosRepository.findById(id);
    }

    public ArticulosMenuAlergenos save(ArticulosMenuAlergenos articuloMenuAlergeno) {
        return articulosMenuAlergenosRepository.save(articuloMenuAlergeno);
    }

    public void delete(Long id) {
        articulosMenuAlergenosRepository.deleteById(id);
    }

}
