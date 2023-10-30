package up.code.codeup.dto.faseDto;

import lombok.Builder;
import lombok.Data;
import up.code.codeup.dto.exercicioDto.ExercicioResponseDto;

import java.util.List;
@Data
@Builder
public class FaseExercicioResponseDto {
    private Integer numFase;
    private String titulo;
    private List<ExercicioResponseDto> exercicios;
}
