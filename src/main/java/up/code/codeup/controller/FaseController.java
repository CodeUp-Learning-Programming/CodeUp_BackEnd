package up.code.codeup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<Fase>> listarFases() {
        List<Fase> fases = faseService.buscarFases();
        return ResponseEntity.status(200).body(fases);
    }

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> criar(@RequestBody @Valid FaseCriacaoDTO faseCriacaoDTO) {
        this.faseService.criar(faseCriacaoDTO);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Fase> atualizarFase(@PathVariable int id, @RequestBody Fase faseAtualizada) {
        if (faseService.atualizarFase(faseAtualizada, id) != null) {
            return ResponseEntity.status(200).body(faseAtualizada);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Fase> deletarFase(@PathVariable int id) {
        if (faseService.deletarFase(id)) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Fase> buscarFasePorId(@PathVariable int id) {
        if (faseService.buscarFasePorId(id) != null) {
            return ResponseEntity.status(200).body(faseService.buscarFasePorId(id));
        }
        return ResponseEntity.status(404).build();
    }
}
