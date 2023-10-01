package up.code.codeup.mapper;

import up.code.codeup.entity.materia.Materia;

public class MateriaMapper {
    public static Materia of(up.code.codeup.service.materia.dto.MateriaCriacaoDto materiaCriacaoDto) {
        Materia materia = new Materia();
        materia.setNome(materiaCriacaoDto.getNome());

        return materia;
    }
}
