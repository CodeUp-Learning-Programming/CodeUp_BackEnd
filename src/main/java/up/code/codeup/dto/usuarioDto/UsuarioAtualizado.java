package up.code.codeup.dto.usuarioDto;

import up.code.codeup.dto.lojaDto.ItemLojaAdquiridoLoginDto;
import up.code.codeup.entity.Usuario;

import java.util.List;

public record UsuarioAtualizado (
        Integer moedas,
        Integer nivel,
        Integer xp,
        List<ItemLojaAdquiridoLoginDto> itensAdquiridos
){
    public UsuarioAtualizado(Usuario usuario){
        this(
                usuario.getMoedas(),
                usuario.getNivel(),
                usuario.getXp(),
                usuario.getItemAdquiridos().stream()
                        .map(itemAdquirido -> new ItemLojaAdquiridoLoginDto(itemAdquirido.getItemLoja(), itemAdquirido.isEquipado())).toList()
        );
    }

}

