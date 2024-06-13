package up.code.codeup.dto.amizadeDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RespostaSolicitacao {
    private Integer idReceptor;
    private String emailSolicitante;
    private boolean resposta;
}
