package up.code.codeup.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import up.code.codeup.entity.Exercicio;
import up.code.codeup.repository.ExercicioRepository;

import java.io.*;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Service
public class ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;


    @Transactional
    public void uploadExercicioCSV(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            Exercicio exercicio = new Exercicio(); // Substitua Exercicio pelo nome da sua entidade
            exercicio.setNomeArquivo(file.getOriginalFilename());

            // Lê o conteúdo do arquivo como um array de bytes
            byte[] arquivoBytes = file.getBytes();

            // Salva o conteúdo do arquivo como um BLOB
            exercicio.setConteudoArquivo(arquivoBytes);

            // Salve o objeto Exercicio no banco de dados
            exercicioRepository.save(exercicio);
        }
    }

    public Exercicio buscarExercicio(Integer id_fase, Integer num_exercicio) {
        FileReader arquivo = null;
        Scanner entrada = null;
        boolean deuRuim = false;
        Exercicio exercicioDesejado = new Exercicio();
        String nomeArquivo = "fases/algoritmos/fase" + id_fase + "_exercicio" + num_exercicio + ".csv";

        System.out.println(nomeArquivo);
        //Tentando abrir o arquivo
        try {
            arquivo = new FileReader(nomeArquivo.exercicioReposito());
            entrada = new Scanner(arquivo).useDelimiter(";|\\n");
        } catch (FileNotFoundException erro) {
            System.out.println("Arquivo não encontrado!");
            return null;
        }

        //Tentando ler o arquivo
        try {
            String conteudo_teorico = entrada.next();
            String desafio = entrada.next();
            String instrucao = entrada.next();
            String layout_funcao = entrada.next();
            String exercicio_desejado = entrada.next();

        } catch (NoSuchElementException erro) {
            System.out.println("Arquivo com problemas");
            erro.printStackTrace();
        } catch (IllegalStateException erro) {
            System.out.println("Erro na leitura do arquivo");
            erro.printStackTrace();
        } finally {
            entrada.close();

            try {
                arquivo.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.out.println("O arquivo ficou aberto!");
            }
        }
        return exercicioDesejado;
    }


}
