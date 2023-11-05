package up.code.codeup.mapper;

import lombok.Builder;
import up.code.codeup.dto.exercicioDto.ExercicioCriacaoDto;
import up.code.codeup.dto.exercicioDto.ExercicioResponseDto;
import up.code.codeup.entity.Exercicio;

@Builder
public class ExercicioMapper {

    public static Exercicio paraEntidade(ExercicioCriacaoDto exercicioCriacaoDTO) {
        return Exercicio.builder()
                .numExercicio(exercicioCriacaoDTO.getNumExercicio())
                .conteudoTeorico(exercicioCriacaoDTO.getConteudoTeorico())
                .desafio(exercicioCriacaoDTO.getDesafio())
                .instrucao(exercicioCriacaoDTO.getInstrucao())
                .layoutFuncao(exercicioCriacaoDTO.getLayoutFuncao())
                .resposta(exercicioCriacaoDTO.getResposta())
                .fase(exercicioCriacaoDTO.getFase())
                .build();
    }

    public static ExercicioResponseDto paraExercicioResponseDto(Exercicio exercicio) {
        return ExercicioResponseDto.builder()
                .numExercicio(exercicio.getNumExercicio())
                .conteudoTeorico(exercicio.getConteudoTeorico())
                .desafio(exercicio.getDesafio())
                .instrucao(exercicio.getInstrucao())
                .layoutFuncao(exercicio.getLayoutFuncao())
                .resposta(exercicio.getResposta())
                .build();
    }

}
