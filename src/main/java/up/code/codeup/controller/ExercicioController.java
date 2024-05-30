package up.code.codeup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.code.codeup.dto.exercicioDto.ExercicioResponseDto;
import up.code.codeup.entity.Usuario;
import up.code.codeup.service.ExercicioService;
import up.code.codeup.service.ExercicioUsuarioService;
import up.code.codeup.dto.js.JsResult;
import up.code.codeup.utils.UsuarioUtils;

import java.io.IOException;
import java.util.List;

// ##########################################
// ATENÇÃO, NÃO APAGAR ESSE COMENTÁRIO
// ##########################################

@RestController
@RequestMapping("api/exercicios")
@RequiredArgsConstructor
public class ExercicioController {
    private final ExercicioService service;
    private final UsuarioUtils usuarioUtils;
    private final ExercicioUsuarioService exercicioUsuarioService;

    @GetMapping("/{idFase}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ExercicioResponseDto>> buscarExerciciosPorIdFase(@PathVariable @NotNull Integer idFase) {
        List<ExercicioResponseDto> exercicios = service.buscarExerciciosPorIdFase(idFase)
                .stream()
                .flatMap(exercicio -> usuarioUtils.getUsuarioLogadoCompleto().getExerciciosUsuarios().stream()
                        .filter(exercicioUsuario -> exercicioUsuario.getExercicio().getId() == exercicio.getId())
                        .map(exercicioUsuario -> new ExercicioResponseDto(exercicio, exercicioUsuario.getResposta_usuario())))
                .toList();

        if (exercicios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.ok(exercicios);
    }

    @GetMapping("/js")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<JsResult> testJavaScriptCode(@RequestParam String funcao, @RequestParam Integer idExercicio, @RequestParam Integer idFase) {
        Usuario usuario = usuarioUtils.getUsuarioLogadoCompleto();

        System.out.println("Quantidade atual de vidas antes de perder: " + usuario.getVida());

        JsResult jsResult = new JsResult();
        try (Context context = Context.newBuilder("js")
                .option("engine.WarnInterpreterOnly", "false")
                .build()) {
            Thread executionThread = new Thread(() -> {
                try {
                    Value resultado = context.eval(Source.newBuilder("js", funcao, "nome_do_arquivo.js").build());
                    System.out.println("Resultado: " + resultado.as(Object.class));
                    System.out.println("Passou: " + Boolean.TRUE.equals(resultado.as(Object.class)));
                    System.out.println("Mensagem: " + resultado);
                    jsResult.setResultado(resultado.as(Object.class));
                    jsResult.setPassou(Boolean.TRUE.equals(jsResult.getResultado()));
                    exercicioUsuarioService.concluiuExercicio(idExercicio, idFase, usuario);
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

             if(!jsResult.getPassou()){
                 usuarioUtils.diminuirVida();
                 jsResult.setVidas(usuario.getVida());
                 System.out.println("Quantidade atual de vidas depois de perder: " + usuarioUtils.getUsuarioLogadoCompleto().getVida());
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
