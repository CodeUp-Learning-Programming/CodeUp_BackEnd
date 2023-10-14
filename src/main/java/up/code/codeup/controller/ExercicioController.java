package up.code.codeup.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.code.codeup.dto.exercicioDto.ExercicioCriacaoDTO;
import up.code.codeup.dto.exercicioDto.ExercicioDTO;
import up.code.codeup.entity.Exercicio;
import up.code.codeup.service.ExercicioService;
import java.util.List;

@RestController
@RequestMapping("/exercicios")
@RequiredArgsConstructor
public class ExercicioController {

    private final ExercicioService exercicioService;

    @PostMapping
    public ResponseEntity<ExercicioCriacaoDTO> criar(@RequestBody @Valid ExercicioCriacaoDTO exercicioCriacaoDTO) {
        this.exercicioService.criar(exercicioCriacaoDTO);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercicio> atualizarExercicio(@PathVariable int id, @RequestBody Exercicio materiaAtualizado) {
        if (exercicioService.atualizarExercicio(materiaAtualizado, id) != null) {
            return ResponseEntity.status(200).body(materiaAtualizado);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Exercicio> deletarExercicio(@PathVariable int id) {
        if (exercicioService.deletarExercicio(id)) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercicio> buscarExercicioPorId(@PathVariable int id) {
        if (exercicioService.buscarExercicioPorId(id) != null) {
            return ResponseEntity.status(200).body(exercicioService.buscarExercicioPorId(id));
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/{fk_fase}/{numExercicio}")
    public ResponseEntity<ExercicioDTO> buscarExercicio(@PathVariable Integer fk_fase, @PathVariable Integer numExercicio){
        ExercicioDTO exercicioDesejado = this.exercicioService.buscarExercicio(fk_fase, numExercicio);
        return ResponseEntity.ok(exercicioDesejado);
    }

    @GetMapping("/{fk_fase}")
    public ResponseEntity<List<ExercicioDTO>> buscarTodosExercicioFase(@PathVariable Integer fk_fase){
        List<ExercicioDTO> exercicioDesejados = this.exercicioService.buscarExercicioPorNumExercicio(fk_fase);

        if(exercicioDesejados.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(exercicioDesejados);
    }
}
