package up.code.codeup.mapper;

import up.code.codeup.dto.faseDto.FaseCriacaoDto;
import up.code.codeup.dto.faseDto.FaseResponseDto;
import up.code.codeup.entity.Fase;

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


}
