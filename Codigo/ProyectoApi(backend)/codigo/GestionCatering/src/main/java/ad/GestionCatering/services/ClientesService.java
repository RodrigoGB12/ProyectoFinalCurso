package ad.GestionCatering.services;

import ad.GestionCatering.models.Clientes;
import ad.GestionCatering.repositories.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;

    public List<Clientes> findAll() {
        return clientesRepository.findAll();
    }

    public Optional<Clientes> findById(Long id) {
        return clientesRepository.findById(id);
    }

    public Clientes save(Clientes cliente) {
        return clientesRepository.save(cliente);
    }

    public void delete(Long id) {
        clientesRepository.deleteById(id);
    }

}
