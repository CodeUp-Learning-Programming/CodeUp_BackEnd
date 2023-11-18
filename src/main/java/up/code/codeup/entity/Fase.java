package up.code.codeup.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer numFase;
    private String titulo;

    @ManyToOne
    @JsonBackReference
    private Materia materia;

    @OneToMany(mappedBy = "fase")
    private List<Exercicio> exercicios = new ArrayList<>();

}
