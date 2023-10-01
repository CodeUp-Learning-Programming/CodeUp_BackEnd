package up.code.codeup.dto.usuarioDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UsuarioLoginDTO {
    @Size(min = 10, max = 60, message = "Campo email  deve ter min 10 caracteres max 60")
    @Email(message = "Campo email não foi reconhecido como email válido")
    private String email;
    @Size(min = 6, max = 15, message = "Campo senha deve ter min 3 caracteres max 25")
    @Pattern(regexp = "^(?!.*\\s)(?!.*\\s$)(?!.*\\s\\s)[\\S\\s]*$", message = "Campo senha inválida - regex")
    private String senha;

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
