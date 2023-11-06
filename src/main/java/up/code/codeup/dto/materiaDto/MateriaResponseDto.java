package up.code.codeup.dto.materiaDto;

import lombok.*;
import up.code.codeup.entity.Materia;

public record MateriaResponseDto(Integer id, String nome) {
    public MateriaResponseDto(Materia materia) {
        this(materia.getId(), materia.getNome());
    }
}
