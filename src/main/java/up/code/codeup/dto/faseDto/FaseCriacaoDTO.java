package up.code.codeup.dto.faseDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import up.code.codeup.entity.Exercicio;
import up.code.codeup.entity.Materia;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FaseCriacaoDTO {

    private Integer numFase;
    @NotNull(message = "Campo titulo obrigatório")
    @Size(min = 2, max = 50, message = "O tamanho minimo é 2 e o máximo é 50 caracteres")
    private String titulo;
    private Materia materia;
    private List<Exercicio> exercicios = new ArrayList<>();
}
