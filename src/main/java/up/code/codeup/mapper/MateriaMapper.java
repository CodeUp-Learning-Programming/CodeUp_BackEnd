package up.code.codeup.mapper;


import up.code.codeup.dto.faseDto.FaseResponseDto;
import up.code.codeup.dto.materiaDto.MateriaCriacaoDto;
import up.code.codeup.dto.materiaDto.MateriaFaseResponseDto;
import up.code.codeup.entity.Fase;
import up.code.codeup.entity.Materia;

import java.util.ArrayList;
import java.util.List;

public class MateriaMapper {
    public static Materia paraEntidade(MateriaCriacaoDto materiaCriacaoDto) {
        Materia materia = new Materia();
        materia.setNome(materiaCriacaoDto.getNome());

        return materia;
    }
    public static MateriaFaseResponseDto paraMateriaFaseDto(Materia materia) {
        List<Fase> fases = materia.getFases();
        List<FaseResponseDto> fasesDto = new ArrayList<>();

        for (Fase fase: fases) {
           fasesDto.add(FaseMapper.paraFaseResponseDto(fase));
        }

        return MateriaFaseResponseDto.builder()
                .nome(materia.getNome())
                .fases(fasesDto)
                .build();
        }

    }


