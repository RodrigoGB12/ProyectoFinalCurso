package ad.GestionCatering.repositories;


import ad.GestionCatering.models.ArticulosMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticulosMenuRepository extends JpaRepository<ArticulosMenu,Long> {
}
