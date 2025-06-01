package ad.GestionCatering.repositories;

import ad.GestionCatering.models.ArticulosMenuAlergenos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticulosMenuAlergenosRepository extends JpaRepository<ArticulosMenuAlergenos,Long> {
}
