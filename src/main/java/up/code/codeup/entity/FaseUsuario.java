package up.code.codeup.entity;

import jakarta.persistence.*;

@Entity
public class FaseUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private boolean desbloqueada;
    private String resposta_usuario;

    @ManyToOne
    private Fase fase;

    @ManyToOne
    private Usuario usuario;
}
