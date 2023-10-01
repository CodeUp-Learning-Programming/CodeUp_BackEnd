package up.code.codeup.mapper;


import up.code.codeup.dto.materiaDto.MateriaCriacaoDto;
import up.code.codeup.entity.Materia;

public class MateriaMapper {
    public static Materia of(MateriaCriacaoDto materiaCriacaoDto) {
        Materia materia = new Materia();
        materia.setNome(materiaCriacaoDto.getNome());

        return materia;
    }
}
