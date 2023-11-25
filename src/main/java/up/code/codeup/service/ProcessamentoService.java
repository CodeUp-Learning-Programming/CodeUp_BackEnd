package up.code.codeup.service;

import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import up.code.codeup.dto.usuarioDto.UsuarioCriacaoDto;
import up.code.codeup.entity.Materia;
import up.code.codeup.entity.Usuario;
import up.code.codeup.mapper.UsuarioMapper;
import up.code.codeup.repository.MateriaRepository;
import up.code.codeup.repository.UsuarioRepository;
import up.code.codeup.utils.FilaObj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class ProcessamentoService {

    private final UsuarioRepository usuarioRepository;
    private final MateriaService materiaService;
    private final MateriaRepository materiaRepository;

    @Scheduled(fixedRate = 60000) // Executa a cada minuto, ajuste conforme necessário
    public List<UsuarioCriacaoDto> executarAgendado(List<MultipartFile> files) {
        FilaObj<MultipartFile> fila;
        fila = new FilaObj<>(10);

        for (MultipartFile file : files) {
            fila.enfileirar(file);
        }
        System.out.println("Caiu no executar agendado");

        List<UsuarioCriacaoDto> listaLida0 = new ArrayList<>();
        while (!fila.estaVazia()){
            MultipartFile arquivo = fila.desenfileirar();
            List<UsuarioCriacaoDto> listaLida2 = leArquivoTxt(arquivo);
            listaLida0.addAll(listaLida2);
        }

        return listaLida0;
    }
    public List<UsuarioCriacaoDto> leArquivoTxt(MultipartFile conteudo) {
        BufferedReader entrada = null;
        String registro, tipoRegistro;
        String nome,idMateria, cpf, plano;
        String email = null;
        LocalDate dtNascimento;
        int contaRegDadosLidos = 0;
        int qtdRegDadosGravados;

        List<UsuarioCriacaoDto> listaLida = new ArrayList<>();
        try {
            entrada = new BufferedReader(new InputStreamReader(conteudo.getInputStream()));
            // BufferedReader entrada = new BufferedReader(new InputStreamReader(conteudo.getInputStream()));
            //
        } catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo");
        }

        try {
            registro = entrada.readLine();

            while (registro != null) {
                tipoRegistro = registro.substring(0, 2);

                if (tipoRegistro.equals("00")) {
                    System.out.println("É um registro de header");
                    System.out.println("Tipo de arquivo: " + registro.substring(2, 11));
                    System.out.println("Data do upload do arquivo: " + registro.substring(11, 30));
                    System.out.println("Versão do documento de layout: " + registro.substring(30, 32));

                } else if (tipoRegistro.equals("01")) {
                    System.out.println("É um registro de trailer");
                    qtdRegDadosGravados = Integer.parseInt(registro.substring(2, 6));
                    if (contaRegDadosLidos == qtdRegDadosGravados) {
                        System.out.println("Quantidade de reg de dados gravados é compatível com " +
                                "a quantidade de reg de dados lidos");
                    } else {
                        System.out.println("Quantidade de reg de dados gravados é incompatível com " +
                                "a quantidade de reg de dados lidos");
                    }
                } else if (tipoRegistro.equals("02")) {
                    System.out.println("É um registro de corpo");
                    nome = registro.substring(2, 27).trim();
                    System.out.println(nome);
                    email = registro.substring(27, 52).trim();
                    System.out.println(email);
                    dtNascimento = LocalDate.parse(registro.substring(52, 62).trim());
                    System.out.println(dtNascimento);
                    // = registro.substring(62, 73).trim();
                    //System.out.println(cpf);
                    //plano = registro.substring(73, 81).trim();
                    //System.out.println(plano);

                    // Incrementa o contador de reg de dados lidos
                    contaRegDadosLidos++;

                    // Cria um objeto Aluno com os dados lidos do registro
                    UsuarioCriacaoDto u = new UsuarioCriacaoDto();
                    u.setNome(nome);
                    u.setEmail(email);
                    u.setDtNascimento(dtNascimento);
                   // u.setCpf(cpf);
                   // u.setPlano(plano);


                    // Se estivesse conectado a um banco de dados
                    usuarioRepository.save(UsuarioMapper.of(u));
                    System.out.println("Adiconou");
                    listaLida.add(u);
                    System.out.println("Adionou na lista");
                } else if (tipoRegistro.equals("03")) {
                    System.out.println("É um registro de corpo");
                    idMateria = registro.substring(2,13).trim();
                    // Incrementa o contador de reg de dados lidos
                    contaRegDadosLidos++;

                    Materia usuarioMateria = new Materia();


                    System.out.println("Tentando encontrar Materia com ID: " + idMateria);
                    if (materiaService != null) {
                        usuarioMateria.setTitulo(idMateria);
                    } else {
                        System.out.println("materiaService é nulo. Certifique-se de que está sendo injetado corretamente.");
                    }
                        materiaRepository.save(usuarioMateria);
                }else {
                    System.out.println("Registro inválido");
                }
                // Le o proximo registro
                registro = entrada.readLine();

            }

            entrada.close();
        } catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo");
            erro.printStackTrace();
        } finally {
            // Certifique-se de fechar o arquivo mesmo em caso de exceção
            if (entrada != null) {
                try {
                    entrada.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return listaLida;
        }
    }
}
