package up.code.codeup.entity.view;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class ViewFaseResult {
    String materiaNome;
    Integer materiaId;
    String faseTitulo;
    Integer faseId;
    String exercicioTitulo;
    Integer exercicioId;
    boolean concluido;
    @Id
    Integer usuarioId;
}
