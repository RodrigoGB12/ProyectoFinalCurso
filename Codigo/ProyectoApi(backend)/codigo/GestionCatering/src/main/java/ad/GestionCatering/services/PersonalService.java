package ad.GestionCatering.services;

import ad.GestionCatering.models.Personal;
import ad.GestionCatering.repositories.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalService {

    @Autowired
    private PersonalRepository personalRepository;

    public List<Personal> findAll() {
        return personalRepository.findAll();
    }

    public Optional<Personal> findById(Long id) {
        return personalRepository.findById(id);
    }

    public Personal save(Personal personal) {
        return personalRepository.save(personal);
    }

    public void delete(Long id) {
        personalRepository.deleteById(id);
    }

}
