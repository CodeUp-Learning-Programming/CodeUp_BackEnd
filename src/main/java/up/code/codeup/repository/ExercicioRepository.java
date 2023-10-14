package up.code.codeup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import up.code.codeup.entity.Exercicio;

import java.util.List;
import java.util.Optional;

public interface ExercicioRepository extends JpaRepository <Exercicio, Integer> {

    @Query("SELECT e FROM Exercicio e WHERE e.fase.id = :fk_fase AND e.numExercicio = :numExercicio")
    Optional<Exercicio> buscarExercicioPorNumero(Integer fk_fase, Integer numExercicio);

    List<Exercicio> findByFase(Integer fk_fase);
}
