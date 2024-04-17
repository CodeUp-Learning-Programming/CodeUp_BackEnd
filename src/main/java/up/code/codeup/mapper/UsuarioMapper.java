package up.code.codeup.mapper;

import up.code.codeup.dto.lojaDto.ItemLojaAdquiridoLoginDto;
import up.code.codeup.dto.usuarioDto.UsuarioCriacaoDto;
import up.code.codeup.dto.usuarioDto.UsuarioTokenDto;
import up.code.codeup.entity.Usuario;

import java.util.Base64;

public class UsuarioMapper {
    public static Usuario of(UsuarioCriacaoDto usuarioCriacaoDto) {
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setDtNascimento(usuarioCriacaoDto.getDtNascimento());
        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setSenha(usuarioCriacaoDto.getSenha());
        return usuario;
    }

    public static UsuarioTokenDto of(Usuario usuario, String token) {
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setId(usuario.getId());
        if (usuario.getFotoPerfil() != null) {
            usuarioTokenDto.setFotoPerfil(usuario.getFotoPerfil());
        } else {
            usuarioTokenDto.setFotoPerfil(null);
        }
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setToken(token);
        usuarioTokenDto.setMoedas(usuario.getMoedas());
        usuarioTokenDto.setNivel(usuario.getNivel());
        usuarioTokenDto.setXp(usuario.getXp());
        usuarioTokenDto.setVidas(usuario.getVidas());
        usuarioTokenDto.setItensAdquiridos(usuario.getItemAdquiridos().stream()
                .map(itemAdquirido -> new ItemLojaAdquiridoLoginDto(itemAdquirido.getItemLoja(), itemAdquirido.isEquipado())).toList());

        return usuarioTokenDto;
    }
}
