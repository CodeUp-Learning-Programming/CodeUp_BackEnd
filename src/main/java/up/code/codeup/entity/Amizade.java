package up.code.codeup.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Amizade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_solicitante")
    private Usuario solicitante;

    @ManyToOne
    @JoinColumn(name = "id_receptor")
    private Usuario receptor;

    private Integer status;
}
