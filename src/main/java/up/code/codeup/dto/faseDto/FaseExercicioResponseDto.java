package up.code.codeup.dto.faseDto;

import lombok.Builder;
import lombok.Data;
import up.code.codeup.dto.exercicioDto.ExercicioResponseDto;
import up.code.codeup.entity.Fase;

import java.util.List;

public record FaseExercicioResponseDto(
        Integer id,
        Integer numFase,
        String titulo,
        int qtdExerciciosConcluidos;
) {
    public FaseExercicioResponseDto(Fase fase, int exerciciosConcluidos) {
        this(
                fase.getId(),
                fase.getNumFase(),
                fase.getTitulo(),
                exerciciosConcluidos
        );
    }
}