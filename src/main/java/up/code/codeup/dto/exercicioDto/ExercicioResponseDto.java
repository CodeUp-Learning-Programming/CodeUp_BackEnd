package up.code.codeup.dto.exercicioDto;

import lombok.*;
import up.code.codeup.entity.Exercicio;
import up.code.codeup.entity.Fase;

public record ExercicioResponseDto(
        Integer id,
        Integer numExercicio,
        String conteudoTeorico,
        String desafio,
        String instrucao,
        String layoutFuncao,
        String resposta
) {
    public ExercicioResponseDto(Exercicio exercicio) {
        this(exercicio.getId(),
                exercicio.getNumExercicio(),
                exercicio.getConteudoTeorico(),
                exercicio.getDesafio(),
                exercicio.getInstrucao(),
                exercicio.getLayoutFuncao(),
                exercicio.getResposta());
    }
}