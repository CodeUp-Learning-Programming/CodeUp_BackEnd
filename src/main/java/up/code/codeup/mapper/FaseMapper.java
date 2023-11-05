package up.code.codeup.mapper;

import up.code.codeup.dto.exercicioDto.ExercicioResponseDto;
import up.code.codeup.dto.faseDto.FaseCriacaoDto;
import up.code.codeup.dto.faseDto.FaseExercicioResponseDto;
import up.code.codeup.dto.faseDto.FaseResponseDto;
import up.code.codeup.entity.Exercicio;
import up.code.codeup.entity.Fase;

import java.util.ArrayList;
import java.util.List;

public class FaseMapper {
    public static Fase paraEntidade(FaseCriacaoDto faseCriacaoDTO) {
        return Fase.builder()
                .numFase(faseCriacaoDTO.getNumFase())
                .titulo(faseCriacaoDTO.getTitulo())
                .materia(faseCriacaoDTO.getMateria())
                .exercicios(faseCriacaoDTO.getExercicios())
                .build();
    }

    public static FaseResponseDto paraFaseResponseDto(Fase fase) {

        return FaseResponseDto.builder()
                .numFase(fase.getNumFase())
                .titulo(fase.getTitulo())
                .build();

    }

    public static FaseExercicioResponseDto paraFaseExercicioResponseDto(Fase fase) {

        List<Exercicio> exercicios = fase.getExercicios();
        List<ExercicioResponseDto> exerciosDto = new ArrayList<>();

        for (Exercicio exercicio: exercicios) {
            exerciosDto.add(ExercicioMapper.paraExercicioResponseDto(exercicio));
        }


        return FaseExercicioResponseDto.builder()
                .numFase(fase.getNumFase())
                .titulo(fase.getTitulo())
                .exercicios(exerciosDto)
                .build();

    }
}
