package ad.GestionCatering.services;

import ad.GestionCatering.models.Pedidos;
import ad.GestionCatering.repositories.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidosService {

    @Autowired
    private PedidosRepository pedidosRepository;

    public List<Pedidos> findAll() {
        return pedidosRepository.findAll();
    }

    public Optional<Pedidos> findById(Long id) {
        return pedidosRepository.findById(id);
    }

    public Pedidos save(Pedidos pedido) {
        return pedidosRepository.save(pedido);
    }

    public void delete(Long id) {
        pedidosRepository.deleteById(id);
    }

}
