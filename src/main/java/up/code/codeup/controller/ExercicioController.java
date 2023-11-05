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

    private final ExercicioService exercicioService;


    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Exercicio> buscarExercicioPorId(@PathVariable int id) {
        if (exercicioService.buscarExercicioPorId(id) != null) {
            return ResponseEntity.status(200).body(exercicioService.buscarExercicioPorId(id));
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/{fk_fase}/{numExercicio}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ExercicioResponseDto> buscarExercicio(@PathVariable Integer fk_fase, @PathVariable Integer numExercicio){
        ExercicioResponseDto exercicioDesejado = this.exercicioService.buscarExercicio(fk_fase, numExercicio);
        return ResponseEntity.ok(exercicioDesejado);
    }

    @GetMapping("/{fk_fase}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ExercicioResponseDto>> buscarTodosExercicioFase(@PathVariable Integer fk_fase){
        List<ExercicioResponseDto> exercicioDesejados = this.exercicioService.buscarExercicioPorNumExercicio(fk_fase);

        if(exercicioDesejados.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(exercicioDesejados);
    }
}
