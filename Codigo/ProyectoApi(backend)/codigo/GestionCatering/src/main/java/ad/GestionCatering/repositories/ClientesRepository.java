package ad.GestionCatering.repositories;

import ad.GestionCatering.models.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes,Long> {
}
