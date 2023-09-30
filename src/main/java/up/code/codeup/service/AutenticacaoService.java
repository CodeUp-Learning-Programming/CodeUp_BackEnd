package up.code.codeup.service.usuario.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import up.code.codeup.entity.usuario.Usuario;
import up.code.codeup.repository.UsuarioRepository;
import up.code.codeup.service.usuario.autenticacao.dto.UsuarioDetalhesDto;

import java.util.Optional;
@Service
public class AutenticacaoService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(username);
        if (usuarioOpt.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Usuário %s não encontrado", username));
        }
        return new UsuarioDetalhesDto(usuarioOpt.get());
    }
}
