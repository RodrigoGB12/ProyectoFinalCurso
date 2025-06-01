package ad.GestionCatering.services;

import ad.GestionCatering.models.ArticulosPedido;
import ad.GestionCatering.repositories.ArticulosPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticulosPedidoService {

    @Autowired
    private ArticulosPedidoRepository articulosPedidoRepository;

    public List<ArticulosPedido> findAll() {
        return articulosPedidoRepository.findAll();
    }

    public Optional<ArticulosPedido> findById(Long id) {
        return articulosPedidoRepository.findById(id);
    }

    public ArticulosPedido save(ArticulosPedido articuloPedido) {
        return articulosPedidoRepository.save(articuloPedido);
    }

    public void delete(Long id) {
        articulosPedidoRepository.deleteById(id);
    }

}
