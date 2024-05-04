package up.code.codeup.service;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import up.code.codeup.dto.usuarioDto.UsuarioLoginDTO;
import up.code.codeup.dto.usuarioDto.UsuarioTokenDto;
import up.code.codeup.entity.Amizade;
import up.code.codeup.entity.Usuario;
import up.code.codeup.exception.EntidadeNaoEncontradaException;
import up.code.codeup.mapper.UsuarioMapper;
import up.code.codeup.repository.AmizadeRepository;
import up.code.codeup.repository.UsuarioRepository;
import up.code.codeup.utils.StatusPedidoAmizade;
import up.code.codeup.utils.UsuarioUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AmizadeRepository amizadeRepository;
    @Autowired
    private up.code.codeup.configuration.security.jwt.GerenciadorTokenJwt gerenciadorTokenJwt;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioUtils usuarioUtils;

    public List<Usuario> buscarUsuarios() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));
    }

    public Usuario cadastrar(Usuario novoUsuario) {
        if (novoUsuario.getNome().equals("tempUser")) {
            novoUsuario = usuarioUtils.gerarUsuarioTemoporario();
        } else if (repository.existsByEmail(novoUsuario.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");
        }

        novoUsuario.setSenha(passwordEncoder.encode(novoUsuario.getSenha()));
        repository.save(novoUsuario);
        return novoUsuario;
    }

    public void removerPerfil(String senha) {
        if (passwordEncoder.matches(senha, usuarioUtils.getUsuarioLogadoCompleto().getSenha())) {
            repository.delete(usuarioUtils.getUsuarioLogadoCompleto());
            return;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha incorreta");
    }

    public UsuarioTokenDto autenticar(UsuarioLoginDTO usuarioLoginDTO) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(usuarioLoginDTO.getEmail(), usuarioLoginDTO.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado = repository.findByEmail(usuarioLoginDTO.getEmail()).orElseThrow(() -> new ResponseStatusException(404, "Email do usuário não cadastrado", null));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = gerenciadorTokenJwt.generateToken(authentication);
        return UsuarioMapper.of(usuarioAutenticado, token);
    }

    public void atualizarFotoPerfil(@NotNull String foto) {
        repository.setFoto(usuarioUtils.getUsuarioLogado().getId(), foto);
    }

    public void removerFotoPerfil() {
        repository.findById(usuarioUtils.getUsuarioLogado().getId()).ifPresent(usuario -> {
            usuario.setFotoPerfil(null);
            repository.save(usuario);
        });
    }

    public boolean solicitarAmizade(Integer idSolicitante, String emailReceptor){
        boolean conviteEnviado = false;
        Optional<Usuario> optSolicitante = repository.findById(idSolicitante);
        Optional<Usuario> optReceptor = repository.findByEmail(emailReceptor);

        if (optSolicitante.isPresent() && optReceptor.isPresent()){
            Usuario solicitante = optSolicitante.get();
            Usuario receptor = optReceptor.get();
            Optional<Amizade> optAmizadeExistente = amizadeRepository.buscarAmizadeExistente(solicitante.getId(), receptor.getId());
            if(optAmizadeExistente.isEmpty()){
                Amizade amizade = new Amizade();
                amizade.setReceptor(receptor);
                amizade.setSolicitante(solicitante);
                amizade.setStatus(StatusPedidoAmizade.PENDENTE);
                amizadeRepository.save(amizade);
                conviteEnviado = true;
            }
        }
        return conviteEnviado;
    }

}