package up.code.codeup.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExercicioUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private boolean concluido;
    private String resposta_usuario;
    private Integer mesConcluido;

    @ManyToOne
    private Exercicio exercicio;

    @ManyToOne
    private Usuario usuario;
}
