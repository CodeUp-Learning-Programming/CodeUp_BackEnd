package up.code.codeup.dto.faseDto;

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
public class FaseDTO {

    private Integer numFase;
    private String titulo;
    private Materia materia;
    private List<Exercicio> exercicios = new ArrayList<>();
}
