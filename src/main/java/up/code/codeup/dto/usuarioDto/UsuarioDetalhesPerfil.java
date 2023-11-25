package up.code.codeup.dto.usuarioDto;

import up.code.codeup.dto.lojaDto.ItemLojaAdquiridoLoginDto;
import up.code.codeup.entity.Usuario;

import java.util.Base64;
import java.util.List;

public record UsuarioDetalhesPerfil(
        String fotoPerfil,
        String nome,
        String email,
        Integer moedas,
        Integer nivel,
        Integer xp,
        List<ItemLojaAdquiridoLoginDto> itensAdquiridos
) {
    public UsuarioDetalhesPerfil(Usuario usuario) {
        this(
                usuario.getFotoPerfil(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getMoedas(),
                usuario.getNivel(),
                usuario.getXp(),
                usuario.getItemAdquiridos().stream()
                        .map(itemAdquirido -> new ItemLojaAdquiridoLoginDto(itemAdquirido.getItemLoja(), itemAdquirido.isEquipado())).toList()
        );
    }
}
