package up.code.codeup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.code.codeup.dto.exercicioDto.ExercicioResponseDto;
import up.code.codeup.service.ExercicioService;
import up.code.codeup.service.Pilha;
import up.code.codeup.utils.UsuarioUtils;

import java.util.List;

@RestController
@RequestMapping("/exercicios")
@RequiredArgsConstructor
public class ExercicioController {
    private final ExercicioService service;
    private final UsuarioUtils usuarioUtils;
    private final Pilha pilhaService;

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

    @GetMapping("/desfazer")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<String> desfazer() {
        String resultado = pilhaService.desfazer();
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/refazer")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<String> refazer() {
        String resultado = pilhaService.refazer();
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/salvaDefazer")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<String> salvaDefazer(@RequestBody String funcao) {
        pilhaService.salvaDefazer(funcao);
        return ResponseEntity.created(null).build();
    }

}
