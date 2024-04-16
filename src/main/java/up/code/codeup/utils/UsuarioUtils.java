package up.code.codeup.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import up.code.codeup.dto.usuarioDto.UsuarioDetalhesDto;
import up.code.codeup.entity.ItemLoja;
import up.code.codeup.entity.Usuario;
import up.code.codeup.repository.UsuarioRepository;

@RequiredArgsConstructor
@Component
public class UsuarioUtils {
    private final UsuarioRepository repository;

    public UsuarioDetalhesDto getUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UsuarioDetalhesDto) authentication.getPrincipal();
    }

    public Usuario gerarUsuarioTemoporario() {
        int totalTempUsers = repository.countByNomeContainsIgnoreCase("tempUser");
        Usuario usuarioTemp = new Usuario();
        usuarioTemp.setEmail("tempUser" + (totalTempUsers + 1) + "@tempmail.com");
        usuarioTemp.setDtNascimento(usuarioTemp.getDtNascimento());
        usuarioTemp.setNome(usuarioTemp.getNome() + (totalTempUsers + 1));

        return usuarioTemp;
    }

    public Usuario getUsuarioLogadoCompleto() {
        return repository.findById(getUsuarioLogado().getId()).get();
    }

    public boolean usuarioPossuiItem(ItemLoja itemLoja) {
        Usuario usuario = repository.findById(getUsuarioLogado().getId()).get();
        return usuario.getItemAdquiridos().stream()
                .anyMatch(itemAdquirido -> itemAdquirido.getItemLoja().equals(itemLoja));
    }

    public void diminuirVida(){
        Usuario usuario = getUsuarioLogadoCompleto();
        usuario.setVidas(usuario.getVidas()-1);
        repository.save(usuario);
    }
}
