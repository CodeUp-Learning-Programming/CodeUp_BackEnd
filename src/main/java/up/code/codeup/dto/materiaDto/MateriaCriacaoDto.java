package up.code.codeup.dto.materiaDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MateriaCriacaoDto {
    @NotNull(message = "Campo nome obrigatório")
    @Size(min = 2, max = 10, message = "O tamanho minimo é 2 e o máximo é 10 caracteres")
    private String nome;
}
