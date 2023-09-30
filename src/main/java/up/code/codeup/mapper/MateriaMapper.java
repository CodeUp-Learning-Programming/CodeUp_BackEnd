package up.code.codeup.service.materia.dto;

import up.code.codeup.entity.materia.Materia;

public class MateriaMapper {
    public static Materia of(MateriaCriacaoDto materiaCriacaoDto) {
        Materia materia = new Materia();
        materia.setNome(materiaCriacaoDto.getNome());

        return materia;
    }
}
