package up.code.codeup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import up.code.codeup.entity.view.ViewFaseResult;

import java.util.List;

public interface ViewFaseResultRepository extends JpaRepository<ViewFaseResult, Integer> {
    List<ViewFaseResult> findByMateriaIdAndUsuarioIdAndConcluidoTrue(Integer idMateria, Integer idUsuario);
}
