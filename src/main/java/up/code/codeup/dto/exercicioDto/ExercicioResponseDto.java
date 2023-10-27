package up.code.codeup.dto.exercicioDto;

import lombok.*;
import up.code.codeup.entity.Fase;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExercicioResponseDto {
    
    private Integer numExercicio;
    private String conteudoTeorico;
    private String desafio;
    private String instrucao;
    private String layoutFuncao;
    private String resposta;
    private Fase fase;
}
