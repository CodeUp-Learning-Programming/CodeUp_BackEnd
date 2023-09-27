package up.code.codeup.service.materia.dto;

import jakarta.validation.constraints.Size;

public class MateriaCriacaoDto {
    @Size(min = 2, max = 10)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
