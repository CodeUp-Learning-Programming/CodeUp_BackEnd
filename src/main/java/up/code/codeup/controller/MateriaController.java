package up.code.codeup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.code.codeup.dto.materiaDto.MateriaResponseDto;
import up.code.codeup.entity.Materia;
import up.code.codeup.service.MateriaService;

import java.util.List;

@RestController
@RequestMapping("/materias")
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

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Materia> buscarMateriaPorId(@PathVariable int id) {
        if (service.buscarMateriaPorId(id) != null) {
            return ResponseEntity.status(200).body(service.buscarMateriaPorId(id));
        }
        return ResponseEntity.status(404).build();
    }
}
