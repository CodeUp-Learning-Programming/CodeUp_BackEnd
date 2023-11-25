package up.code.codeup.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "Usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonIgnore
    @Column(length = 50 * 1024 * 1024)
    private String fotoPerfil;
    private String nome;
    private String email;
    private String senha;
    private LocalDate dtNascimento;
    private Integer moedas;
    private Integer xp;

    @ColumnDefault("0")
    private Integer nivel;
    @ColumnDefault("0")

    @OneToMany(mappedBy = "usuario")
    private List<ExercicioUsuario> exerciciosUsuarios;

    @OneToMany(mappedBy = "usuario")
    private List<ItemAdquirido> itemAdquiridos;
}
