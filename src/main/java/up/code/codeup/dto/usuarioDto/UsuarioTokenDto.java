package up.code.codeup.dto.usuarioDto;

import lombok.Getter;
import lombok.Setter;
import up.code.codeup.dto.lojaDto.ItemLojaAdquiridoLoginDto;

import java.util.List;

@Getter
@Setter
public class UsuarioTokenDto {
    private Integer id;
    private String nome;
    private String email;
    private String token;
    private Integer moedas;
    private List<ItemLojaAdquiridoLoginDto> itensAdquiridos;

}
