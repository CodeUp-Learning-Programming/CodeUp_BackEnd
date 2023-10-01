package up.code.codeup.dto.usuarioDto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UsuarioCriacaoDto {
    @NotNull(message = "Campo nome obrigatório")
    @Size(min = 3, max = 25, message = "Campo nome deve ter min 3 caracteres max 25")
    @Pattern(regexp = "^(?!\\s)(?!.*\\s$)(?!.*\\s{2,})[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$",
    message = "Campo nome inválido - regex" )
    private String nome;
    @Past(message = "Campo data nascimento deve estar no passado")
    private LocalDate dtNascimento;

    @Size(min = 10, max = 60, message = "Campo email  deve ter min 10 caracteres max 60")
    @Email(message = "Campo email não foi reconhecido como email válido")
    private String email;
    @Size(min = 6, max = 15, message = "Campo senha deve ter min 3 caracteres max 25")
    @Pattern(regexp = "^(?!.*\\s)(?!.*\\s$)(?!.*\\s\\s)[\\S\\s]*$", message = "Campo senha inválida - regex")
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }
    public String getEmail() {
        return email;
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
