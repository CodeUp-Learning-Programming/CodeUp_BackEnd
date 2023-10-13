package up.code.codeup.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.code.codeup.dto.materiaDto.MateriaCriacaoDto;
import up.code.codeup.entity.Materia;
import up.code.codeup.service.MateriaService;

import java.util.List;

@RestController
@RequestMapping("/materias")
@RequiredArgsConstructor
public class MateriaController {

    private final MateriaService materiaService;

    @GetMapping
    public ResponseEntity<List<Materia>> listarMaterias() {
        List<Materia> materias = materiaService.buscarMaterias();
        return ResponseEntity.status(200).body(materias);
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid MateriaCriacaoDto materiaCriacaoDto) {
        this.materiaService.criar(materiaCriacaoDto);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Materia> atualizarMateria(@PathVariable int id, @RequestBody Materia materiaAtualizado) {
        if (materiaService.atualizarMateria(materiaAtualizado, id) != null) {
            return ResponseEntity.status(200).body(materiaAtualizado);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Materia> deletarMateria(@PathVariable int id) {
        if (materiaService.deletarMateria(id)) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materia> buscarMateriaPorId(@PathVariable int id) {
        if (materiaService.buscarMateriaPorId(id) != null) {
            return ResponseEntity.status(200).body(materiaService.buscarMateriaPorId(id));
        }
        return ResponseEntity.status(404).build();
    }
}
