package up.code.codeup.entity.fase;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import up.code.codeup.entity.materia.Materia;

@Entity
public class Fase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_fase;
    private Integer num_fase;
    private String nome;
    private String descricao;
    private String conteudo_exec;
    @ManyToOne
    @JoinColumn(name = "fk_materia")
    @JsonBackReference
    private Materia materia;

    public Integer getId_fase() {
        return id_fase;
    }

    public void setId_fase(Integer id_fase) {
        this.id_fase = id_fase;
    }

    public Integer getNum_fase() {
        return num_fase;
    }

    public void setNum_fase(Integer num_fase) {
        this.num_fase = num_fase;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getConteudo_exec() {
        return conteudo_exec;
    }

    public void setConteudo_exec(String conteudo_exec) {
        this.conteudo_exec = conteudo_exec;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}
