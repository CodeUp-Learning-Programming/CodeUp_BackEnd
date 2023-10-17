package up.code.codeup.arquivo;
import org.jetbrains.annotations.NotNull;
import up.code.codeup.entity.Usuario;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UsuarioCsvImporter {

    public static void gravaUsuariosEmArquivoCsv(@NotNull List<Usuario> usuarios, String nomeArq) {
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

    public static void lerExibirArquivoCsv(String nomeArq) {
        nomeArq += ".csv";

        try (FileReader arq = new FileReader(nomeArq);
             Scanner entrada = new Scanner(arq).useDelimiter(";|\\n")) {

            System.out.printf("%-4s %-15s %-6s %-10s %-15s %-5s\n", "ID", "NOME", "EMAIL", "SENHA", "DTNASCIMENTO");
            while (entrada.hasNext()) {
                int id = entrada.nextInt();
                String nome = entrada.next();
                String email = entrada.next();
                String senha = entrada.next();
                String dtNascimento = entrada.next();
                System.out.printf("%04d %-15s %-6s %-10s %-15s %5s\n", id, nome, email, senha, dtNascimento);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
