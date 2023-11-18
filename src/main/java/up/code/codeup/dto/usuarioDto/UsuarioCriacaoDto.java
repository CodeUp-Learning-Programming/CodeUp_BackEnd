package up.code.codeup.dto.usuarioDto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCriacaoDto {

    @NotNull(message = "Campo nome obrigatório")
    @Size(min = 3, max = 45, message = "Campo nome deve ter min 3 caracteres max 45")
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
}
