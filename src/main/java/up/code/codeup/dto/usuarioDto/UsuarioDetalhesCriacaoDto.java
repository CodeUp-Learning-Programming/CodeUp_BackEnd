package up.code.codeup.dto.usuarioDto;

import up.code.codeup.entity.Usuario;

public record UsuarioDetalhesCriacaoDto(String nome, String email) {
    public UsuarioDetalhesCriacaoDto(Usuario usuario) {
        this(usuario.getNome(), usuario.getEmail());
    }
}
