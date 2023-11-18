package up.code.codeup.entity;

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
    private String nome;
    private String email;
    private String senha;
    private LocalDate dtNascimento;
    private Integer moedas;

    @ColumnDefault("0")
    private Integer nivel;

    @OneToMany(mappedBy = "usuario")
    private List<ExercicioUsuario> exerciciosUsuarios;

    @OneToMany(mappedBy = "usuario")
    private List<ItemAdquirido> itemAdquiridos;
}
