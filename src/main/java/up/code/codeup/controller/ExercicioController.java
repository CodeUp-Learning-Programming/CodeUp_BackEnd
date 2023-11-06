package up.code.codeup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.code.codeup.dto.exercicioDto.ExercicioResponseDto;
import up.code.codeup.entity.Exercicio;
import up.code.codeup.service.ExercicioService;

import java.util.List;

@RestController
@RequestMapping("/exercicios")
@RequiredArgsConstructor
public class ExercicioController {

    private final ExercicioService service;

    @GetMapping("/{idFase}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ExercicioResponseDto>> buscarExerciciosPorIdFase(@PathVariable Integer idFase) {
        List<ExercicioResponseDto> exercicios = service.buscarExerciciosPorIdFase(idFase)
                .stream()
                .map(exercicio -> new ExercicioResponseDto(exercicio)).toList();

        if (exercicios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.ok(exercicios);
    }
}
