package up.code.codeup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import up.code.codeup.dto.faseDto.FaseExercicioResponseDto;
import up.code.codeup.dto.materiaDto.MateriaFaseResponseDto;
import up.code.codeup.entity.Fase;
import up.code.codeup.service.FaseService;


import java.util.List;

@RestController
@RequestMapping("/fases")
@RequiredArgsConstructor
public class FaseController {

    private final FaseService faseService;

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<Fase>> listarFases() {
        List<Fase> fases = faseService.buscarFases();
        return ResponseEntity.status(200).body(fases);
    }

    @GetMapping("/exercicios")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<FaseExercicioResponseDto>> buscarFaseExercicios() {
        List<FaseExercicioResponseDto> fases = faseService.buscarFaseExercicios();
        return ResponseEntity.status(200).body(fases);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<FaseExercicioResponseDto> buscarFaseExerciciosPorId(@PathVariable int id) {
        if (faseService.buscarFaseExerciciosPorId(id) != null) {
            return ResponseEntity.status(200).body(faseService.buscarFaseExerciciosPorId(id));
        }
        return ResponseEntity.status(404).build();
    }
}
