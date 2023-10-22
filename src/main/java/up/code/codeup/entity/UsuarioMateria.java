package up.code.codeup.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioMateria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private boolean concluido;

    @ManyToOne
    @JoinColumn(name = "fk_exercicio")
    private Materia materia;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;
}
