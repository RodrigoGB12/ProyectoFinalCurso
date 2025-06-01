package ad.GestionCatering.repositories;

import ad.GestionCatering.models.Alergenos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlergenosRepository extends JpaRepository<Alergenos,Long> {
}
