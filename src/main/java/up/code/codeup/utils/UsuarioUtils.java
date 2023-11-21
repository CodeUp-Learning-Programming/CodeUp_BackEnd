package up.code.codeup.utils;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import up.code.codeup.dto.usuarioDto.UsuarioDetalhesDto;
import up.code.codeup.entity.ItemLoja;
import up.code.codeup.entity.Usuario;
import up.code.codeup.repository.UsuarioRepository;

@RequiredArgsConstructor
@Component
public class UsuarioUtils {
    private final UsuarioRepository usuarioRepository;

    public UsuarioDetalhesDto getUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UsuarioDetalhesDto) authentication.getPrincipal();
    }

    public Usuario getUsuarioLogadoCompleto() {
        return usuarioRepository.findById(getUsuarioLogado().getId()).get();
    }

    public boolean usuarioPossuiItem(ItemLoja itemLoja) {
        Usuario usuario = usuarioRepository.findById(getUsuarioLogado().getId()).get();
        return usuario.getItemAdquiridos().stream()
                .anyMatch(itemAdquirido -> itemAdquirido.getItemLoja().equals(itemLoja));
    }
}
