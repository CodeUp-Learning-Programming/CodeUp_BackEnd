package up.code.codeup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.code.codeup.dto.exercicioDto.ExercicioResponseDto;
import up.code.codeup.dto.usuarioDto.UsuarioDetalhesDto;
import up.code.codeup.service.ExercicioService;
import up.code.codeup.utils.UsuarioUtils;

import java.util.List;

@RestController
@RequestMapping("/exercicios")
@RequiredArgsConstructor
public class ExercicioController {
    private final ExercicioService service;
    private final UsuarioUtils usuarioUtils;

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
}
