package ad.GestionCatering.services;

import ad.GestionCatering.models.Alergenos;
import ad.GestionCatering.repositories.AlergenosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlergenosService {

    @Autowired
    private AlergenosRepository alergenosRepository;

    public List<Alergenos> findAll() {
        return alergenosRepository.findAll();
    }

    public Optional<Alergenos> findById(Long id) {
        return alergenosRepository.findById(id);
    }

    public Alergenos save(Alergenos alergeno) {
        return alergenosRepository.save(alergeno);
    }

    public void delete(Long id) {
        alergenosRepository.deleteById(id);
    }
}
