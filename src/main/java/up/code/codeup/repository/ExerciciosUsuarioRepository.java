package up.code.codeup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import up.code.codeup.entity.ExercicioUsuario;

@Repository
public interface ExerciciosUsuarioRepository extends JpaRepository<ExercicioUsuario, Integer> {
    boolean existsByExercicioIdAndUsuarioIdAndConcluidoTrue(Integer idExercicio, Integer idUsuario);
}
