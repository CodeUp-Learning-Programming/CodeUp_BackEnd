package up.code.codeup.dto.usuarioDto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UsuarioCriacaoDto {

    @NotNull
    @Size(min = 3, max = 25)
    @Pattern(regexp = "^(?!.*\\s)(?!.*\\s$)(?!.*\\s{2,})[A-Za-zÀ-ÖØ-öø-ÿ]+$\n",
    message = "Campo nome inválido" )
    private String nome;
    @Past
    private LocalDate dtNascimento;
    @Email
    private String email;
    @Size(min = 6, max = 15)
    @Pattern(regexp = "^(?!.*\\s)(?!.*\\s$)(?!.*\\s\\s)[\\S\\s]*$")
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
