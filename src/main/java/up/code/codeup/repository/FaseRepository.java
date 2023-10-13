package up.code.codeup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import up.code.codeup.entity.Fase;

import java.util.UUID;

public interface FaseRepository extends JpaRepository<Fase, Integer> {
}
