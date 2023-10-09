package up.code.codeup.mapper;

import up.code.codeup.dto.exercicioDto.ExercicioCriacaoDto;
import up.code.codeup.entity.Exercicio;
import up.code.codeup.entity.Fase;

public class ExercicioMapper {

    //Criando exercicio para enviar ao front
    public static Exercicio of(ExercicioCriacaoDto exercicioCriacaoDto) {
        Exercicio exercicio = new Exercicio();
        exercicio.setConcluido(exercicioCriacaoDto.isConcluido());
        exercicio.setConteudo_teorico(exercicioCriacaoDto.getConteudo_teorico());
        exercicio.setDesafio(exercicioCriacaoDto.getDesafio());
        exercicio.setInstrucao(exercicioCriacaoDto.getInstrucao());
        exercicio.setLayout_funcao(exercicioCriacaoDto.getLayout_funcao());
        exercicio.setResposta(exercicioCriacaoDto.getResposta());
        exercicio.setFase(exercicioCriacaoDto.getFase());

        return exercicio;
    }

}
