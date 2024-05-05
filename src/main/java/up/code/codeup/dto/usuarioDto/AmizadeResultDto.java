package up.code.codeup.dto.usuarioDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import up.code.codeup.utils.StatusPedidoAmizade;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AmizadeResultDto {
    private String nome;
    private String email;
    private StatusPedidoAmizade status;
}
