package up.code.codeup.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity(name = "Usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    private String nome;
    private String email;
    private String senha;
    private LocalDate dtNascimento;
    @ColumnDefault("''")
    private String cpf;
    @ColumnDefault("'gratuito'")
    private String plano;

    @ColumnDefault("0")
    private Integer moedas;

    @ColumnDefault("0")
    private Integer diamantes;

    @ColumnDefault("0")
    private Integer nivel;

    @ColumnDefault("0")
    private Integer xp;

    @ColumnDefault("0")
    private Integer diasConsecutivos;

    @ColumnDefault("0")
    private Integer maxDiasConsecutivos;

}
