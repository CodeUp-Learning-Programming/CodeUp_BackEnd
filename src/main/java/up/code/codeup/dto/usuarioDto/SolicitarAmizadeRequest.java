package up.code.codeup.dto.usuarioDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitarAmizadeRequest {
    private Integer idSolicitante;
    private String emailReceptor;
}
