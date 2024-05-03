package up.code.codeup.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private Integer moedas = 0;
    private Integer xp = 0;
    private Integer nivel = 0;
    private Integer vida = 5;

    @OneToMany(mappedBy = "usuario")
    private List<ExercicioUsuario> exerciciosUsuarios;

    @OneToMany(mappedBy = "usuario")
    private List<FaseUsuario> fasesUsuarios;

    @OneToMany(mappedBy = "usuario")
    private List<ItemAdquirido> itemAdquiridos;

    @ManyToMany
    @JoinTable(
            name = "Amizade",
            joinColumns = @JoinColumn(name = "id_solicitante"),
            inverseJoinColumns = @JoinColumn(name = "id_receptor")
    )
    private Set<Usuario> amigos = new HashSet<>();

    @Override
    public String toString() {
        return "Usuario:" +
                "id: " + id +
                "\n nome='" + nome + '\'' +
                "\n email='" + email + '\'' +
                "\n senha='" + senha + '\'' +
                "\n dtNascimento=" + dtNascimento +
                "\n moedas=" + moedas +
                "\n xp=" + xp +
                "\n nivel=" + nivel +
                '}';
    }
}
