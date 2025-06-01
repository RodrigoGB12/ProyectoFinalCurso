package ad.GestionCatering.repositories;

import ad.GestionCatering.models.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos,Long> {
}
