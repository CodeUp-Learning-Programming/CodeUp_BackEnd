package up.code.codeup.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer numExercicio;

    @Size(max = 1000)
    private String conteudoTeorico;

    @Size(max = 1000)
    private String desafio;

    @Size(max = 1000)
    private String instrucao;

    @Size(max = 1000)
    private String layoutFuncao;

    @Size(max = 1000)
    private String resposta;

    @ManyToOne
    @JoinColumn(name = "fk_fase")
    @JsonBackReference
    private Fase fase;
}
