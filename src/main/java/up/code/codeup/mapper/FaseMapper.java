package up.code.codeup.mapper;

import up.code.codeup.dto.faseDto.FaseCriacaoDTO;
import up.code.codeup.dto.faseDto.FaseDTO;
import up.code.codeup.entity.Exercicio;
import up.code.codeup.entity.Fase;
import up.code.codeup.entity.Materia;

import java.util.ArrayList;
import java.util.List;

public class FaseMapper {
    public static Fase paraEntidade(FaseCriacaoDTO faseCriacaoDTO) {
        return Fase.builder()
                .numFase(faseCriacaoDTO.getNumFase())
                .titulo(faseCriacaoDTO.getTitulo())
                .materia(faseCriacaoDTO.getMateria())
                .exercicios(faseCriacaoDTO.getExercicios())
                .build();
    }

    public static FaseDTO paraDTO(Fase fase) {
        return FaseDTO.builder()
                .numFase(fase.getNumFase())
                .titulo(fase.getTitulo())
                .materia(fase.getMateria())
                .exercicios(fase.getExercicios())
                .build();
    }

}
