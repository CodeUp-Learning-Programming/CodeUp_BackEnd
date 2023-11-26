package up.code.codeup.dto.materiaDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class MateriaCriacaoDto {
    private String titulo;

    public MateriaCriacaoDto(String titulo) {
        this.titulo = titulo;
    }
}
