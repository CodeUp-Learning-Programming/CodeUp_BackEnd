package up.code.codeup.mapper;

import up.code.codeup.dto.usuarioDto.UsuarioCriacaoDto;
import up.code.codeup.dto.usuarioDto.UsuarioTokenDto;
import up.code.codeup.entity.Usuario;

public class UsuarioMapper {

    public static Usuario of(UsuarioCriacaoDto usuarioCriacaoDto) {
        Usuario usuario = new Usuario();

        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setDtNascimento(usuarioCriacaoDto.getDtNascimento());
        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setSenha(usuarioCriacaoDto.getSenha());
        usuario.setCpf("");
        usuario.setPlano("gratuito");
        usuario.setMoedas(0);
        usuario.setDiamantes(0);
        usuario.setNivel(0);
        usuario.setXp(0);
        usuario.setDiasConsecutivos(0);
        usuario.setMaxDiasConsecutivos(0);


        return usuario;
    }

    public static UsuarioTokenDto of(Usuario usuario, String token) {
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setId(usuario.getIdUsuario());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setToken(token);

        return usuarioTokenDto;
    }
}
