package up.code.codeup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import up.code.codeup.dto.usuarioDto.UsuarioDetalhesDto;
import up.code.codeup.entity.Usuario;
import up.code.codeup.repository.UsuarioRepository;

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

    public static void desfazer(){
        
    }
}
