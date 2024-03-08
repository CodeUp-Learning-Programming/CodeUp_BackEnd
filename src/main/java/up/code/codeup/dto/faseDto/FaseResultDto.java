package up.code.codeup.dto.faseDto;

public record FaseResultDto(
        Integer faseId,
        Integer numFase,
        String tituloFase,
        Integer qtdExerciciosFase,
        Integer qtdExerciciosFaseConcluidos,
        boolean desbloqueada
) {
}
