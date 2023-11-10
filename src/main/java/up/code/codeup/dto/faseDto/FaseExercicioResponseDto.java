package up.code.codeup.dto.faseDto;

import up.code.codeup.entity.Fase;

public record FaseExercicioResponseDto(
        Integer id,
        Integer numFase,
        String titulo,
        int qtdExercicios,
        int qtdExerciciosConcluidos
) {
    public FaseExercicioResponseDto(Fase fase, int qtdExercicios, int exerciciosConcluidos) {
        this(
                fase.getId(),
                fase.getNumFase(),
                fase.getTitulo(),
                qtdExercicios,
                exerciciosConcluidos
        );
    }
}