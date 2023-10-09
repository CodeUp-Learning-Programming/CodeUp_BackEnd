package up.code.codeup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import up.code.codeup.entity.Exercicio;

import java.util.Optional;

public interface ExercicioRepository extends JpaRepository <Exercicio, Integer> {

}
