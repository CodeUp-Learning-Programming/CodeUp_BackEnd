package up.code.codeup.dto.usuarioDto;

import up.code.codeup.entity.Usuario;

public record UsuarioRankingDto(String nome, Integer xp, String foto, String email) {

    public UsuarioRankingDto(Usuario usuario){
        this(usuario.getNome(), usuario.getXp(), usuario.getFotoPerfil(), usuario.getEmail());
    }
}
