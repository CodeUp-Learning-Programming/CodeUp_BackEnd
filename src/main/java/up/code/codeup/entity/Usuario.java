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
//    @ColumnDefault("''")
//    private String cpf;
//    @ColumnDefault("'gratuito'")
//    private String plano;

//    @ColumnDefault("0")
//    private Integer moedas;
//
//    @ColumnDefault("0")
//    private Integer diamantes;
//
    @ColumnDefault("0")
    private Integer nivel;
//
//    @ColumnDefault("0")
//    private Integer xp;
//
//    @ColumnDefault("0")
//    private Integer diasConsecutivos;
//
//    @ColumnDefault("0")
//    private Integer maxDiasConsecutivos;

<<<<<<< HEAD
    @ColumnDefault("0")
    private Integer xp;

    @ColumnDefault("0")
    private Integer diasConsecutivos;

    @ColumnDefault("0")
    private Integer maxDiasConsecutivos;

=======
    @OneToMany(mappedBy = "usuario")
    private List<ExercicioUsuario> exerciciosUsuarios;
>>>>>>> e8ecfeb5850290f4912ea9ff528a73166ff5d623
}
