package up.code.codeup.service;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import up.code.codeup.configuration.security.jwt.GerenciadorTokenJwt;
import up.code.codeup.dto.usuarioDto.UsuarioLoginDTO;
import up.code.codeup.dto.usuarioDto.UsuarioTokenDto;
import up.code.codeup.entity.Usuario;
import up.code.codeup.exception.EntidadeNaoEncontradaException;
import up.code.codeup.mapper.UsuarioMapper;
import up.code.codeup.repository.UsuarioRepository;
import up.code.codeup.utils.UsuarioUtils;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioUtils usuarioUtils;

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario cadastrar(Usuario novoUsuario) {
        if (novoUsuario.getNome().equals("tempUser")) {
            int totalTempUsers = repository.countByNomeContainsIgnoreCase("tempUser");
            novoUsuario.setEmail("tempUser" + (totalTempUsers + 1) + "@tempmail.com");
            novoUsuario.setDtNascimento(novoUsuario.getDtNascimento());
            novoUsuario.setNome(novoUsuario.getNome() + (totalTempUsers + 1));
            novoUsuario.setSenha(passwordEncoder.encode(novoUsuario.getSenha()));
            this.repository.save(novoUsuario);
            return novoUsuario;
        }
        repository.findByEmail(novoUsuario.getEmail()).ifPresent(usuarioExistente -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");
        });

        repository.findByNomeIgnoreCase(novoUsuario.getNome()).ifPresent(usuarioExistente -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nome já cadastrado");
        });

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
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDTO.getEmail(), usuarioLoginDTO.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                repository.findByEmail(usuarioLoginDTO.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = gerenciadorTokenJwt.generateToken(authentication);
        return UsuarioMapper.of(usuarioAutenticado, token);
    }

    public void atualizarFotoPerfil(@NotNull byte[] foto) {
        repository.setFoto(usuarioUtils.getUsuarioLogado().getId(), foto);
    }

    public void removerFotoPerfil() {
        repository.findById(usuarioUtils.getUsuarioLogado().getId()).ifPresent(usuario -> {
            usuario.setFotoPerfil(null);
            repository.save(usuario);
        });
    }

}
//    public void gravaUsuariosEmArquivoCsv(ListaObj<Usuario> usuarioListaObj, String nomeArq) {
//        nomeArq += ".csv";
//        //Titulo
//        try (FileWriter arquivoCsv = new FileWriter(nomeArq)) {
//            String titulo = String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
//                    "IdUsuario",
//                    "Nome",
//                    "Email",
//                    "DtNascimento",
//                    "Cpf",
//                    "Plano",
//                    "Moedas",
//                    "Diamantes",
//                    "Nivel",
//                    "Xp",
//                    "DiasConsecutivos",
//                    "MaxDiasConsecutivos"
//            );
//
//            arquivoCsv.write(titulo);
//
//            //Linha
//            for (int i = 0; i < usuarioListaObj.getTamanho(); i++) {
//                Usuario usuario = usuarioListaObj.buscaPorIndice(i);
//                String linha = String.format("%d;%s;%s;%s;%s;%s;%d;%d;%d;%d;%d;%d\n",
//                        usuario.getIdUsuario(),
//                        usuario.getNome(),
//                        usuario.getEmail(),
//                        usuario.getDtNascimento(),
//                        usuario.getCpf(),
//                        usuario.getPlano(),
//                        usuario.getMoedas(),
//                        usuario.getDiamantes(),
//                        usuario.getNivel(),
//                        usuario.getXp(),
//                        usuario.getDiasConsecutivos(),
//                        usuario.getMaxDiasConsecutivos()
//                );
//
//                arquivoCsv.write(linha);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Transactional
//    public void saveUsuariosFromCsv(MultipartFile file) {
//        try {
//            CSVParser csvParser = new CSVParserBuilder()
//                    .withSeparator(';') // Configura o delimitador como ponto e vírgula
//                    .build();
//
//            CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()));
//
//            String[] line;
//            while ((line = reader.readNext()) != null) {
//                Usuario usuario = new Usuario();
//                usuario.setIdUsuario(Integer.parseInt(line[0]));
//                usuario.setNome(line[1]);
//                usuario.setEmail(line[2]);
//                usuario.setSenha(line[3]);
//                // Parse LocalDate from line[4] if needed
//
//                // Save the Usuario entity to the H2 Database
//                entityManager.persist(usuario);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (CsvValidationException e) {
//            throw new RuntimeException(e);
//        }
//    }


