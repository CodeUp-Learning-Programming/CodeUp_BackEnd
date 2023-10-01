package up.code.codeup.dto.materiaDto;

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
