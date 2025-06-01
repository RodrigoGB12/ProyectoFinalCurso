package ad.GestionCatering.repositories;

import ad.GestionCatering.models.ArticulosPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticulosPedidoRepository extends JpaRepository<ArticulosPedido,Long> {
}
