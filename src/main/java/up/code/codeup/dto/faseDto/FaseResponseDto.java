package up.code.codeup.dto.faseDto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.ManyToOne;
import lombok.*;
import up.code.codeup.entity.Exercicio;
import up.code.codeup.entity.Materia;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FaseResponseDto {
    private Integer numFase;
    private String titulo;
}
