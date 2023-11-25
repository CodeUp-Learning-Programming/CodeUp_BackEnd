package up.code.codeup.tests;

import lombok.RequiredArgsConstructor;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import up.code.codeup.service.ExercicioUsuarioService;
import up.code.codeup.utils.UsuarioUtils;

import java.io.IOException;

@RequestMapping("/testes")
@RestController
@RequiredArgsConstructor
public class JsValidation {
    private final ExercicioUsuarioService exercicioUsuarioService;

    @GetMapping("/js")
    public ResponseEntity<JsResult> testJavaScriptCode(@RequestParam String funcao, @RequestParam Integer idExercicio) {
        JsResult jsResult = new JsResult();

        try (Context context = Context.newBuilder("js")
                .option("engine.WarnInterpreterOnly", "false")
                .build()) {

            Thread executionThread = new Thread(() -> {
                try {
                    Value resultado = context.eval(Source.newBuilder("js", funcao, "nome_do_arquivo.js").build());
                    jsResult.setResultado(resultado.as(Object.class));
                    jsResult.setPassou(Boolean.TRUE.equals(jsResult.getResultado()));
                    exercicioUsuarioService.concluiuExercicio(idExercicio);
                } catch (PolyglotException e) {
                    System.err.println("Erro ao executar código JavaScript: " + e.getMessage());
                    jsResult.setResultado(e.getMessage());
                    jsResult.setPassou(false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            executionThread.start();
            executionThread.join(10000); // Aguarde no máximo 10 segundos para a execução da thread

            if (executionThread.isAlive()) {
                // Se a thread ainda estiver viva após o tempo limite, interrompa-a
                executionThread.interrupt();
                jsResult.setResultado("Tempo limite da execução do exercicio excedido! Cuidado com loop infinito!.");
                jsResult.setPassou(false);
            }

            return ResponseEntity.ok(jsResult);

        } catch (Exception e) {
            System.err.println("Erro durante a execução do código: " + e.getMessage());
            jsResult.setResultado("Erro durante a execução do código.");
            jsResult.setPassou(false);
            return ResponseEntity.status(200).body(jsResult);
        }
    }
}
