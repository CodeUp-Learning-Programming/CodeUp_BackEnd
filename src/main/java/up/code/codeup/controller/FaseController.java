package up.code.codeup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.code.codeup.dto.faseDto.FaseExercicioResponseDto;
import up.code.codeup.service.ExercicioUsuarioService;
import up.code.codeup.service.FaseService;
import up.code.codeup.utils.UsuarioUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/fases")
@RequiredArgsConstructor
public class FaseController {

    private final FaseService service;
    private final ExercicioUsuarioService exercicioUsuarioService;

    @GetMapping("/{idMateria}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity buscarFasesPorIdMateria(@PathVariable int idMateria) {
        List<FaseExercicioResponseDto> fases = service.buscarFasesPorIdMateria(idMateria)
                .stream()
                .map(fase -> {
                    int qtdExercicios = fase.getExercicios().size();
                    AtomicInteger qtdExerciciosConcluidos = new AtomicInteger();
                    fase.getExercicios().forEach(exercicio -> {
                        if (exercicioUsuarioService.verificarExercicioConcluido(exercicio.getId(), UsuarioUtils.getUsuarioLogado().getId())) {
                            qtdExerciciosConcluidos.getAndIncrement();
                        }
                    });
                    return new FaseExercicioResponseDto(fase, qtdExercicios, qtdExerciciosConcluidos.get());
                }).toList();
        if (fases.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(fases);
    }
}
