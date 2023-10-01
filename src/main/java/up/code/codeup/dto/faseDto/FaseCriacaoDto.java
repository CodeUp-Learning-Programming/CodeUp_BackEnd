package up.code.codeup.dto.faseDto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import up.code.codeup.entity.Materia;

public class FaseCriacaoDto {

    private Integer num_fase;
    @Size(min = 3, max = 50)
    private String nome;
    @Size(min = 1, max = 255)

    private String descricao;
    @Size(min = 1, max = 255)
    private String conteudo_exec;
    private Materia materia;

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
