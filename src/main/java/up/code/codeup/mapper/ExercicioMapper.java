package up.code.codeup.mapper;

import jakarta.persistence.Lob;
import up.code.codeup.dto.exercicioDto.ExercicioCriacaoDto;
import up.code.codeup.entity.Exercicio;
import up.code.codeup.entity.Fase;

public class ExercicioMapper {

    //Criando exercicio para enviar ao front
    public static Exercicio of(ExercicioCriacaoDto exercicioCriacaoDto) {
        Exercicio exercicio = new Exercicio();
        exercicio.setConcluido(exercicioCriacaoDto.isConcluido());
        exercicio.setNum_exercicio((exercicioCriacaoDto.getNum_exercicio()));
        exercicio.setConteudoArquivo(exercicioCriacaoDto.getConteudoArquivo());
        exercicio.setNomeArquivo(exercicioCriacaoDto.getNomeArquivo());
        exercicio.setFase(exercicioCriacaoDto.getFase());

        return exercicio;
    }

}
