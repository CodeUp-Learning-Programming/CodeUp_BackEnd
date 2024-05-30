package up.code.codeup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.code.codeup.dto.materiaDto.MateriaCriacaoDto;
import up.code.codeup.dto.materiaDto.MateriaResponseDto;
import up.code.codeup.entity.Materia;
import up.code.codeup.service.MateriaService;

import java.util.List;

// ##########################################
// ATENÇÃO, NÃO APAGAR ESSE COMENTÁRIO
// ##########################################

@RestController
@RequestMapping("api/materias")
@RequiredArgsConstructor
public class MateriaController {
    private final MateriaService service;

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<MateriaResponseDto>> listar() {
        List<MateriaResponseDto> materias = service.buscarMaterias()
                .stream()
                .map(materia -> new MateriaResponseDto(materia)).toList();

        if (materias.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(materias);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        service.buscarMateriaPorId(id);
        service.deletarMateria(id);
        return ResponseEntity.status(200).build();
    }

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> salvar(@RequestBody MateriaCriacaoDto dto) {
        Materia materia = new Materia();
        materia.setTitulo(dto.getTitulo());
        service.salvarMateria(materia);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> atualizar(@PathVariable int id, @RequestBody MateriaCriacaoDto dto) {
        Materia materiaEncontrada = service.buscarMateriaPorId(id);
        materiaEncontrada.setTitulo(dto.getTitulo());
        service.salvarMateria(materiaEncontrada);
        return ResponseEntity.status(200).build();
    }


}
