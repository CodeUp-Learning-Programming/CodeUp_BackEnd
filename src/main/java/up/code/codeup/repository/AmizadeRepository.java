package up.code.codeup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import up.code.codeup.entity.Amizade;
import java.util.Optional;

public interface AmizadeRepository extends JpaRepository<Amizade, Integer> {
    Optional<Amizade> findByIdReceptorAndIdSolicitante (int idReceptor, int idSolicitante);
}
