package up.code.codeup.dto.usuarioDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import up.code.codeup.dto.lojaDto.ItemLojaAdquiridoLoginDto;

import java.util.List;

@Getter
@Setter
public class UsuarioTokenDto {
    private Integer id;
    private String fotoPerfil;
    private String nome;
    private String email;
    private String token;
    private Integer moedas;
    private Integer nivel;
    private Integer xp;

    private List<ItemLojaAdquiridoLoginDto> itensAdquiridos;

}
