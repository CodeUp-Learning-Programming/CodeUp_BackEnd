package up.code.codeup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciciosUsuarioRepository extends JpaRepository<ExerciciosUsuarioRepository, Integer> {
    int CountByExercicioIdAndUsuarioIdAndConcluidoTrue(int idExercicio, int idUsuario);
}
