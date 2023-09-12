package up.code.codeup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import up.code.codeup.entity.usuario.UsuarioEntity;
import up.code.codeup.entity.usuario.UsuarioLoginDTO;
import up.code.codeup.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioEntity> buscarUsuarios() {
        return usuarioRepository.findAll();
    }

    public UsuarioEntity cadastrarUsuario(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }

    public UsuarioEntity atualizarUsuario(UsuarioEntity novoUsuairo, int id) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            UsuarioEntity usuarioExistente = usuario.get();
            usuarioExistente.setNome(novoUsuairo.getNome());
            usuarioExistente.setEmail(novoUsuairo.getEmail());
            usuarioExistente.setSenha(novoUsuairo.getSenha());
            usuarioRepository.save(usuarioExistente);
            return usuarioExistente;
        }
        return null;
    }

    public boolean deletarUsuario(int id) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findById(id);
        usuario.ifPresent(u -> usuarioRepository.delete(u));
        return true;
    }

    public UsuarioEntity buscarUsuarioPorId(int id) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            UsuarioEntity usuarioExistente = usuario.get();
            return usuarioExistente;
        }
        return null;
    }

    public boolean validarLogin(UsuarioLoginDTO login) {
        UsuarioEntity usuario = usuarioRepository.findByEmail(login.getEmail());
        if (usuario != null) {
            if (usuario.getSenha().equals(login.getSenha())) {
                return true;
            }
        }
        return false;
    }

}
