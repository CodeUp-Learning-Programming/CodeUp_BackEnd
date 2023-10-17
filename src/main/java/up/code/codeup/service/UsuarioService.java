package up.code.codeup.service;

import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import up.code.codeup.configuration.security.jwt.GerenciadorTokenJwt;
import up.code.codeup.dto.usuarioDto.UsuarioCriacaoDto;
import up.code.codeup.dto.usuarioDto.UsuarioLoginDTO;
import up.code.codeup.dto.usuarioDto.UsuarioTokenDto;
import up.code.codeup.entity.Usuario;
import up.code.codeup.mapper.UsuarioMapper;
import up.code.codeup.repository.UsuarioRepository;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;
    @Autowired
    private AuthenticationManager authenticationManager;

    public List<Usuario> buscarUsuarios() {
        return usuarioRepository.findAll();
    }

    public void criar(UsuarioCriacaoDto usuarioCriacaoDto) {
        final Usuario novoUsuario = UsuarioMapper.of(usuarioCriacaoDto);
        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);
        this.usuarioRepository.save(novoUsuario);
    }

    public Usuario atualizarUsuario(Usuario novoUsuairo, int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            Usuario usuarioExistente = usuario.get();
            usuarioExistente.setNome(novoUsuairo.getNome());
            usuarioExistente.setEmail(novoUsuairo.getEmail());
            usuarioExistente.setSenha(novoUsuairo.getSenha());
            usuarioRepository.save(usuarioExistente);
            return usuarioExistente;
        }
        return null;
    }

    public boolean deletarUsuario(int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        usuario.ifPresent(u -> usuarioRepository.delete(u));
        return true;
    }

    public Usuario buscarUsuarioPorId(int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            Usuario usuarioExistente = usuario.get();
            return usuarioExistente;
        }
        return null;
    }

    public UsuarioTokenDto autenticar(UsuarioLoginDTO usuarioLoginDTO) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDTO.getEmail(), usuarioLoginDTO.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                usuarioRepository.findByEmail(usuarioLoginDTO.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = gerenciadorTokenJwt.generateToken(authentication);
        return UsuarioMapper.of(usuarioAutenticado, token);
    }


    public void gravaUsuariosEmArquivoCsv(List<Usuario> usuarios, String nomeArq) {
        nomeArq += ".csv";

        try (FileWriter arquivoCsv = new FileWriter(nomeArq)) {
            for (Usuario usuario : usuarios) {
                String linha = String.format("%d;%s;%s;%s;%s\n",
                        usuario.getIdUsuario(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getSenha(),
                        usuario.getDtNascimento());

                arquivoCsv.write(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lerExibirArquivoCsv(String nomeArq) {
        nomeArq += ".csv";

        try (FileReader arq = new FileReader(nomeArq);
             Scanner entrada = new Scanner(arq).useDelimiter(";|\\n")) {

            System.out.printf("%-4s %-15s %-17s %-60s %-15s\n", "ID", "NOME", "EMAIL", "SENHA", "DTNASCIMENTO");
            while (entrada.hasNext()) {
                try {
                    int id = Integer.parseInt(entrada.next());
                    String nome = entrada.next();
                    String email = entrada.next();
                    String senha = entrada.next();
                    String dtNascimento = entrada.next();
                    System.out.printf("%04d %-15s %-17s %-60s %-15s\n", id, nome, email, senha, dtNascimento);
                } catch (NumberFormatException e) {
                    System.err.println("Erro ao converter um valor para inteiro. Ignorando a linha.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


  /*  @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveUsuariosFromCsv(MultipartFile file) {
        try {
            CSVParser csvParser = new CSVParserBuilder()
                    .withSeparator(';') // Configura o delimitador como ponto e vírgula
                    .build();

            CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()));

            String[] line;
            while ((line = reader.readNext()) != null) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(Integer.parseInt(line[0]));
                usuario.setNome(line[1]);
                usuario.setEmail(line[2]);
                usuario.setSenha(line[3]);
                // Parse LocalDate from line[4] if needed

                // Save the Usuario entity to the H2 Database
                entityManager.persist(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

*/
        }
