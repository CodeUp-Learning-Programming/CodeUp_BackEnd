package up.code.codeup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import up.code.codeup.entity.materia.Materia;

public interface MateriaRepository  extends JpaRepository<Materia, Integer> {
}
