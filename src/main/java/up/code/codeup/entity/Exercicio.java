package up.code.codeup.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

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

    @OneToMany(mappedBy = "exercicio")
    private List<ExercicioUsuario> exerciciosUsuarios;

    @ManyToOne
    @JoinColumn(name = "fk_fase")
    private Fase fase;
}
