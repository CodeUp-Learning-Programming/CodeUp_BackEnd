package up.code.codeup.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import up.code.codeup.dto.faseDto.FaseCriacaoDTO;
import up.code.codeup.entity.Fase;
import up.code.codeup.service.FaseService;


import java.util.List;

@RestController
@RequestMapping("/fases")
@RequiredArgsConstructor
public class FaseController {

    private final FaseService faseService;

    @GetMapping
    public ResponseEntity<List<Fase>> listarFases() {
        List<Fase> fases = faseService.buscarFases();
        return ResponseEntity.status(200).body(fases);
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid FaseCriacaoDTO faseCriacaoDTO) {
        this.faseService.criar(faseCriacaoDTO);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fase> atualizarFase(@PathVariable int id, @RequestBody Fase faseAtualizada) {
        if (faseService.atualizarFase(faseAtualizada, id) != null) {
            return ResponseEntity.status(200).body(faseAtualizada);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Fase> deletarFase(@PathVariable int id) {
        if (faseService.deletarFase(id)) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fase> buscarFasePorId(@PathVariable int id) {
        if (faseService.buscarFasePorId(id) != null) {
            return ResponseEntity.status(200).body(faseService.buscarFasePorId(id));
        }
        return ResponseEntity.status(404).build();
    }
}
