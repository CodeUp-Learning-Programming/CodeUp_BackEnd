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
//        usuario.setCpf("");
//        usuario.setPlano("gratuito");
//        usuario.setMoedas(0);
//        usuario.setDiamantes(0);
//        usuario.setNivel(0);
//        usuario.setXp(0);
//        usuario.setDiasConsecutivos(0);
//        usuario.setMaxDiasConsecutivos(0);
        return usuario;
    }
//    public static UsuarioCriacaoDto toDto(Usuario usuario) {
//        UsuarioCriacaoDto usuarioDto = new UsuarioCriacaoDto();
//
//        usuarioDto.setEmail(usuario.getEmail());
//        usuarioDto.setDtNascimento(usuario.getDtNascimento());
//        usuarioDto.setNome(usuario.getNome());
//        usuarioDto.setSenha(usuario.getSenha());
//        usuarioDto.("");
//        usuarioDto.setPlano("gratuito");
//        usuarioDto.setMoedas(0);
//        usuarioDto.setDiamantes(0);
//        usuarioDto.setNivel(0);
//        usuarioDto.setXp(0);
//        usuarioDto.setDiasConsecutivos(0);
//        usuarioDto.setMaxDiasConsecutivos(0);
//
//
//        return usuarioDto;
//    }
    public static UsuarioTokenDto of(Usuario usuario, String token) {
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setId(usuario.getId());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setToken(token);

        return usuarioTokenDto;
    }
}
