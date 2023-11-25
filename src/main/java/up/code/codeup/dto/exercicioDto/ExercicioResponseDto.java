package up.code.codeup.dto.exercicioDto;

import lombok.*;
import up.code.codeup.entity.Exercicio;
import up.code.codeup.entity.Fase;
import up.code.codeup.utils.UsuarioUtils;

public record ExercicioResponseDto(
        Integer id,
        Integer numExercicio,
        String conteudoTeorico,
        String desafio,
        String instrucao,
        String layoutFuncao,
        String respostaUsuario,
        Integer moeda,
        Integer xp
) {
    public ExercicioResponseDto(Exercicio exercicio, String respostaUsuario) {
        this(exercicio.getId(),
                exercicio.getNumExercicio(),
                exercicio.getConteudoTeorico(),
                exercicio.getDesafio(),
                exercicio.getInstrucao(),
                exercicio.getLayoutFuncao(),
                respostaUsuario,
                exercicio.getMoeda(),
                exercicio.getXp()
        );
    }
}