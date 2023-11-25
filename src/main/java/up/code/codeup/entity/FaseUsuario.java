package up.code.codeup.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FaseUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private boolean desbloqueada;
    @ManyToOne
    private Fase fase;
    @ManyToOne
    private Usuario usuario;
}
