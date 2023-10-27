package up.code.codeup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.code.codeup.dto.materiaDto.MateriaCriacaoDto;
import up.code.codeup.dto.materiaDto.MateriaFaseResponseDto;
import up.code.codeup.entity.Materia;
import up.code.codeup.service.MateriaService;

import java.util.List;

@RestController
@RequestMapping("/materias")
@RequiredArgsConstructor
public class MateriaController {

    private final MateriaService materiaService;

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<Materia>> listarMaterias() {
        List<Materia> materias = materiaService.buscarMaterias();
        return ResponseEntity.status(200).body(materias);
    }

    @GetMapping("/fases")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<MateriaFaseResponseDto>> listarMateriasFases() {
        List<MateriaFaseResponseDto> materias = materiaService.buscarMateriasFase();
        return ResponseEntity.status(200).body(materias);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Materia> buscarMateriaPorId(@PathVariable int id) {
        if (materiaService.buscarMateriaPorId(id) != null) {
            return ResponseEntity.status(200).body(materiaService.buscarMateriaPorId(id));
        }
        return ResponseEntity.status(404).build();
    }
}
