package up.code.codeup.service;

import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import up.code.codeup.FilaObj;
import up.code.codeup.dto.usuarioDto.UsuarioCriacaoDto;
import up.code.codeup.entity.Materia;
import up.code.codeup.entity.Usuario;
import up.code.codeup.entity.UsuarioMateria;
import up.code.codeup.mapper.UsuarioMapper;
import up.code.codeup.repository.MateriaRepository;
import up.code.codeup.repository.UsuarioMateriaRepository;
import up.code.codeup.repository.UsuarioRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class ProcessamentoService {
    private FilaObj<MultipartFile> fila;
    private UsuarioRepository usuarioRepository;
    private UsuarioMateriaRepository usuarioMateriaRepository;
    private MateriaRepository materiaRepository;


    public ProcessamentoService(UsuarioRepository usuarioRepository){
        this.fila = new FilaObj<>(10);
        this.usuarioRepository  = usuarioRepository;
    }


    @Scheduled(fixedRate = 60000) // Executa a cada minuto, ajuste conforme necessário
    public List<UsuarioCriacaoDto> executarAgendado() {
        System.out.println("Caiu no executar agendado");
        if (!fila.estaVazia()) {
            MultipartFile arquivo = fila.desenfileirar();
            System.out.println("Arquivo processado: " + arquivo.getOriginalFilename());
            return leArquivoTxt(arquivo);
        }
        return null;
    }
    public List<UsuarioCriacaoDto> leArquivoTxt(MultipartFile conteudo) {
        try{
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conteudo.getInputStream()));
            String registro, tipoRegistro;
            String nome, email,  cpf, plano;
            LocalDate dtNascimento;
            int contaRegDadosLidos = 0;
            int qtdRegDadosGravados, idMateria;

            List<UsuarioCriacaoDto> listaLida = new ArrayList<>();

            registro = entrada.readLine();

            while (registro != null) {
                tipoRegistro = registro.substring(0, 2);

                if (tipoRegistro.equals("00")) {
                    System.out.println("É um registro de header");
                    System.out.println("Tipo de arquivo: " + registro.substring(3, 11));
                    System.out.println("Data e hora de geração do arquivo: " + registro.substring(11, 30));
                    System.out.println("Versão do documento de layout: " + registro.substring(30, 32));

                } else if (tipoRegistro.equals("01")) {
                    System.out.println("É um registro de trailer");
                    qtdRegDadosGravados = Integer.parseInt(registro.substring(2, 12));
                    if (contaRegDadosLidos == qtdRegDadosGravados) {
                        System.out.println("Quantidade de reg de dados gravados é compatível com " +
                                "a quantidade de reg de dados lidos");
                    } else {
                        System.out.println("Quantidade de reg de dados gravados é incompatível com " +
                                "a quantidade de reg de dados lidos");
                    }
                }  else if (tipoRegistro.equals("02")) {
                    System.out.println("É um registro de corpo");
                    nome = registro.substring(3, 27).trim();
                    email = registro.substring(27, 52).trim();
                    dtNascimento = LocalDate.parse(registro.substring(52, 62).trim());
                    cpf = registro.substring(62, 73).trim();
                    plano = registro.substring(73, 81).trim();

                    // Incrementa o contador de reg de dados lidos
                    contaRegDadosLidos++;

                    // Cria um objeto Aluno com os dados lidos do registro
                    UsuarioCriacaoDto u = new UsuarioCriacaoDto();
                    u.setNome(nome);
                    u.setEmail(email);
                    u.setDtNascimento(dtNascimento);
                    u.setCpf(cpf);
                    u.setPlano(plano);


                    // Se estivesse conectado a um banco de dados
                    usuarioRepository.save(UsuarioMapper.of(u));
                    listaLida.add(u);
                }
                else if (tipoRegistro.equals("03")) {
                    System.out.println("É um registro de corpo");
                    idMateria = Integer.parseInt(registro.substring(3,4).trim());
                    // Incrementa o contador de reg de dados lidos
                    contaRegDadosLidos++;

                    UsuarioMateria usuarioMateria = new UsuarioMateria();

                    Materia materia = materiaRepository.findById(idMateria).orElse(null);
                    email = null;
                    Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);

                    if (materia != null && usuario != null) {
                        usuarioMateria.setMateria(materia);
                        usuarioMateria.setUsuario(usuario);

                        usuarioMateriaRepository.save(usuarioMateria);


                }else {
                    System.out.println("Registro inválido");
                }

                // Le o proximo registro
                registro = entrada.readLine();

            }  // fim do while
            // Fecha o arquivo
            entrada.close();
                return listaLida;
        } // fim do try

} catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
