package up.code.codeup.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import up.code.codeup.dto.usuarioDto.UsuarioDetalhesDto;

public class UsuarioUtils {
    public static UsuarioDetalhesDto getUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UsuarioDetalhesDto) authentication.getPrincipal();
    }
}
