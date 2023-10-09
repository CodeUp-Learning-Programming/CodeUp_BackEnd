package up.code.codeup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import up.code.codeup.entity.Exercicio;
import up.code.codeup.repository.ExercicioRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Service
public class ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    public Exercicio buscarExercicio(Integer id_fase, Integer num_exercicio) {
        FileReader arquivo = null;
        Scanner entrada = null;
        boolean deuRuim = false;
        Exercicio exercicioDesejado = new Exercicio();
        String nomeArquivo = "fases\\algoritmos\\fase" + id_fase + "_exercicio" + num_exercicio + ".csv";

        System.out.println(nomeArquivo);
        //Tentando abrir o arquivo
        try {
            arquivo = new FileReader(nomeArquivo);
            entrada = new Scanner(arquivo).useDelimiter(";|\\n");
        } catch (FileNotFoundException erro) {
            System.out.println("Arquivo não encontrado!");
            return null;
        }

        //Tentando ler o arquivo
        try {
            exercicioDesejado.setConteudo_teorico(entrada.next());
            exercicioDesejado.setDesafio(entrada.next());
            exercicioDesejado.setInstrucao(entrada.next());
            exercicioDesejado.setLayout_funcao(entrada.next());
            exercicioDesejado.setResposta(entrada.next());
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
