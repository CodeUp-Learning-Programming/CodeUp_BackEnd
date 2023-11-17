package up.code.codeup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import up.code.codeup.ListaObj;
import up.code.codeup.configuration.security.jwt.GerenciadorTokenJwt;
import up.code.codeup.dto.usuarioDto.UsuarioCriacaoDto;
import up.code.codeup.dto.usuarioDto.UsuarioLoginDTO;
import up.code.codeup.dto.usuarioDto.UsuarioTokenDto;
import up.code.codeup.entity.Usuario;
import up.code.codeup.mapper.UsuarioMapper;
import up.code.codeup.repository.UsuarioRepository;

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

//    @PersistenceContext
//    private static EntityManager entityManager;

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

    private static void gravaRegistroTxt(String registro, String nomeArq) {
        BufferedWriter saida = null;
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq,true));
        }
        catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo");
        }
        try {
            saida.append(registro + "\n");
            saida.close();
        }
        catch (IOException erro) {
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
        }
    }
    public static void gravaArquivoTxt(ListaObj<Usuario> usuarioListaObj, String nomeArq) {
        int contaRegDados = 0;

        // Monta o registro de header
        String header = "00USUARIO20232";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";

        // Grava o registro de header
        gravaRegistroTxt(header, nomeArq);


        // Grava os registros de dados (ou registros de corpo)
        for (int i = 0; i < usuarioListaObj.getTamanho(); i++) {
            Usuario usuario = usuarioListaObj.buscaPorIndice(i);
            String corpo = "02";
                   corpo += String.format("%03d", usuario.getIdUsuario());
                   corpo += String.format("%-25.25s", usuario.getNome());
                   corpo += String.format("%-25.25s", usuario.getEmail());
                   corpo += String.format("%-10.10s", usuario.getDtNascimento());
                   corpo += String.format("%-11.11s", usuario.getCpf());
                   corpo += String.format("-3.3s", usuario.getPlano());
                   corpo += String.format("05d", usuario.getMoedas());
                   corpo += String.format("05d", usuario.getDiamantes());
                   corpo += String.format("03d", usuario.getNivel());
                   corpo += String.format("06d", usuario.getXp());
                   corpo += String.format("03d", usuario.getDiasConsecutivos());
                   corpo += String.format("03d", usuario.getMaxDiasConsecutivos());

            gravaRegistroTxt(corpo, nomeArq);
            contaRegDados++;
        }
        String trailer = "03";
        trailer += String.format("%010d", contaRegDados);

        gravaRegistroTxt(trailer, nomeArq);
    }

//    public void leArquivoTxt(String nomeArq) {
//        BufferedReader entrada = null;
//        String registro, tipoRegistro;
//        String nome, email, dtNascimento, cpf, plano, materia;
//        int contaRegDadosLidos = 0;
//        int qtdRegDadosGravados;
//
//        List<Usuario>  listaLida = new ArrayList<>();
//
//        try {
//            entrada = new BufferedReader(new FileReader(nomeArq));
//        }
//        catch (IOException erro) {
//            System.out.println("Erro na abertura do arquivo");
//        }
//
//        // Leitura do arquivo
//        try {
//            registro = entrada.readLine();
//
//            while (registro != null) {
//                // obtem os 2 primeiros caracteres do registro lido
//                // 1o argumento do substring eh o indice do que se quer obter, iniciando de zero
//                // 2o argumento do substring eh o indice final do que se deseja, MAIS UM
//                // 012345
//                // 00NOTA
//                tipoRegistro = registro.substring(0,2);
//
//                if (tipoRegistro.equals("00")) {
//                    System.out.println("É um registro de header");
//                    System.out.println("Tipo de arquivo: " + registro.substring(3,11));
//                    System.out.println("Data e hora de geração do arquivo: " + registro.substring(11,30));
//                    System.out.println("Versão do documento de layout: " + registro.substring(30,32));
//
//                }
//                else if (tipoRegistro.equals("01")) {
//                    System.out.println("É um registro de trailer");
//                    qtdRegDadosGravados= Integer.parseInt(registro.substring(2,12));
//                    if (contaRegDadosLidos == qtdRegDadosGravados) {
//                        System.out.println("Quantidade de reg de dados gravados é compatível com " +
//                                "a quantidade de reg de dados lidos");
//                    }
//                    else {
//                        System.out.println("Quantidade de reg de dados gravados é incompatível com " +
//                                "a quantidade de reg de dados lidos");
//                    }
//                } else if (tipoRegistro.equals("03")) {
//                    System.out.println("É um registro de corpo");
//                    materia= registro.substring(3,27).trim();
//                    // Incrementa o contador de reg de dados lidos
//                    contaRegDadosLidos++;
//
//                    // Cria um objeto Aluno com os dados lidos do registro
//                    Usuario u = new Usuario(materia);
//
//                    // Se estivesse conectado a um banco de dados
//                    usuarioRepository.save(u);
//
//                    // Como não estamos conectados a um BD, vamos adicionar
//
//                } else if (tipoRegistro.equals("02")) {
//                    System.out.println("É um registro de corpo");
//                    nome= registro.substring(3,27).trim();
//                    email= registro.substring(27,52).trim();
//                    dtNascimento= registro.substring(52,62).trim();
//                    cpf= registro.substring(62,73).trim();
//                    plano= registro.substring(73,81).trim();
//
//                    // Incrementa o contador de reg de dados lidos
//                    contaRegDadosLidos++;
//
//                    // Cria um objeto Aluno com os dados lidos do registro
//                    Usuario u = new Usuario(nome, email, dtNascimento, cpf, plano);
//
//                    // Se estivesse conectado a um banco de dados
//                    usuarioRepository.save(u);
//                }
//                else {
//                    System.out.println("Registro inválido");
//                }
//
//                // Le o proximo registro
//                registro = entrada.readLine();
//
//            }  // fim do while
//            // Fecha o arquivo
//            entrada.close();
//        } // fim do try
//        catch (IOException erro) {
//            System.out.println("Erro ao ler o arquivo");
//            erro.printStackTrace();
//        }
//
//        // Aqui tb seria possível salvar a lista no BD
//        // repository.saveAll(listaLida);
//
//    }

}
