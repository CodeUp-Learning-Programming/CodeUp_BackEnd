package up.code.codeup.service;

import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
import up.code.codeup.ListaObj;
import up.code.codeup.configuration.security.jwt.GerenciadorTokenJwt;
import up.code.codeup.dto.usuarioDto.UsuarioCriacaoDto;
import up.code.codeup.dto.usuarioDto.UsuarioLoginDTO;
import up.code.codeup.dto.usuarioDto.UsuarioTokenDto;
import up.code.codeup.entity.Usuario;
import up.code.codeup.mapper.UsuarioMapper;
import up.code.codeup.repository.UsuarioRepository;

import java.io.*;
import java.util.*;

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
        List<Usuario> usuarios = usuarioRepository.findAll();

        int indiceInferior = 0;
        int indiceSuperior = usuarios.size() - 1;
        int meio;
        while (indiceInferior <= indiceSuperior) {
            meio = (indiceInferior + indiceSuperior) / 2;
            if (id == usuarios.get(meio).getIdUsuario()) {
                return usuarios.get(meio);
            } else if (id < usuarios.get(meio).getIdUsuario()) {
                indiceSuperior = meio - 1;
            } else {
                indiceInferior = meio + 1;
            }
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


    public void gravaUsuariosEmArquivoCsv(ListaObj<Usuario> usuarioListaObj, String nomeArq) {
        nomeArq += ".csv";
        //Titulo
        try (FileWriter arquivoCsv = new FileWriter(nomeArq)) {
            String titulo = String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                    "IdUsuario",
                    "Nome",
                    "Email",
                    "DtNascimento",
                    "Cpf",
                    "Plano",
                    "Moedas",
                    "Diamantes",
                    "Nivel",
                    "Xp",
                    "DiasConsecutivos",
                    "MaxDiasConsecutivos"
            );

            arquivoCsv.write(titulo);

            //Linha
            for (int i = 0; i < usuarioListaObj.getTamanho(); i++) {
                Usuario usuario = usuarioListaObj.buscaPorIndice(i);
                String linha = String.format("%d;%s;%s;%s;%s;%s;%d;%d;%d;%d;%d;%d\n",
                        usuario.getIdUsuario(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getDtNascimento(),
                        usuario.getCpf(),
                        usuario.getPlano(),
                        usuario.getMoedas(),
                        usuario.getDiamantes(),
                        usuario.getNivel(),
                        usuario.getXp(),
                        usuario.getDiasConsecutivos(),
                        usuario.getMaxDiasConsecutivos()
                );

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

            System.out.printf("%-4s %-15s %-6s %-10s %-15s %-5s %5s\n", "ID", "NOME", "EMAIL", "DTNASCIMENTO", "NIVEL", "XP");
            while (entrada.hasNext()) {
                int id = entrada.nextInt();
                String nome = entrada.next();
                String email = entrada.next();
                String dtNascimento = entrada.next();
                int nivel = entrada.nextInt();
                int xp = entrada.nextInt();
                System.out.printf("%04d %-15s %-6s %-10s %-15s %5s %d %d\n", id, nome, email, dtNascimento, nivel, xp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @PersistenceContext
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

}
