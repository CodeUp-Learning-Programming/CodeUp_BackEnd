package up.code.codeup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import up.code.codeup.entity.Fase;

import java.util.List;

public interface FaseRepository extends JpaRepository<Fase, Integer> {
    List<Fase> findByMateriaId(int id);
}
