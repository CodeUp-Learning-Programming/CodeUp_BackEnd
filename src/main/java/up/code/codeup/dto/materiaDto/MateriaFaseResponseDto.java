package up.code.codeup.dto.materiaDto;

import lombok.Builder;
import lombok.Data;
import up.code.codeup.dto.faseDto.FaseResponseDto;

import java.util.List;

@Data
@Builder
public class MateriaFaseResponseDto {
    private String nome;
    private List<FaseResponseDto> fases;
}
