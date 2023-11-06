package up.code.codeup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import up.code.codeup.dto.faseDto.FaseExercicioResponseDto;
import up.code.codeup.entity.Fase;
import up.code.codeup.service.FaseService;
import up.code.codeup.utils.UsuarioUtils;


import java.util.List;

@RestController
@RequestMapping("/fases")
@RequiredArgsConstructor
public class FaseController {

    private final FaseService service;

    @GetMapping("/{idMateria}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity buscarFasesPorIdMateria(@PathVariable int idMateria) {
        List<FaseExercicioResponseDto> fases = service.buscarFasesPorIdMateria(idMateria)
                .stream()
                .map(fase -> new FaseExercicioResponseDto(fase)).toList();

        if (fases.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(fases);
    }
}
