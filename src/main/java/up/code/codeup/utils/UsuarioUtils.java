package up.code.codeup.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import up.code.codeup.dto.usuarioDto.UsuarioDetalhesDto;
import up.code.codeup.entity.ItemLoja;
import up.code.codeup.entity.Usuario;
import up.code.codeup.repository.UsuarioRepository;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class UsuarioUtils {
    private final UsuarioRepository repository;
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

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
        if(usuario.getVida() >= 1){
            usuario.setVida(usuario.getVida()-1);
            if(usuario.getVida() == 0){
                adicionarVidaSchedule();
            }
        }
        repository.save(usuario);
    }

    public void adicionarVidaSchedule() {
        Usuario usuario = getUsuarioLogadoCompleto();
        if (usuario.getVida() < 5) {
            scheduler.schedule(() -> {
                adicionarVidaAoUsuario(usuario);
            }, 1, TimeUnit.MINUTES);
            System.out.println("Caiu no schedule papai");
        }
    }

    private void adicionarVidaAoUsuario(Usuario usuario) {
        if (usuario.getVida() == 0) {
            usuario.setVida(5);
            System.out.println("Chamou o adicionar vidas papai");
            System.out.println("Aqui ó era pra ele ter essas vidas daqui 1 minutinho: " + usuario.getVida());
        }
        repository.save(usuario);
    }

}
