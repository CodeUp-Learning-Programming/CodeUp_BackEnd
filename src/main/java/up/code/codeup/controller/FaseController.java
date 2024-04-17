package up.code.codeup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.code.codeup.dto.faseDto.FaseResultDto;
import up.code.codeup.entity.Materia;
import up.code.codeup.service.FaseService;
import up.code.codeup.service.MateriaService;
import up.code.codeup.utils.UsuarioUtils;

import java.util.List;

@RestController
@RequestMapping("/fases")
@RequiredArgsConstructor
public class FaseController {
    private final FaseService service;
    private final MateriaService materiaService;
    private final UsuarioUtils usuarioUtils;

    @GetMapping("/{idMateria}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<FaseResultDto>> buscarFaseResultPorIdMateriaIdUsuario(@PathVariable @NotNull Integer idMateria) {
        Materia materia = materiaService.buscarMateriaPorId(idMateria);

        List<FaseResultDto> dtos = materia.getFases()
                .stream()
                .map(fase -> {

                    boolean b = fase.getFaseUsuarios().stream()
                            .anyMatch(faseUsuario -> faseUsuario.getUsuario().getId().equals(usuarioUtils.getUsuarioLogado().getId()) && faseUsuario.isDesbloqueada());

                    long qtdExerciciosFaseConcluidos = fase.getExercicios()
                            .stream()
                            .flatMap(exercicio -> exercicio.getExerciciosUsuarios().stream())
                            .filter(exercicioUsuario -> exercicioUsuario.getUsuario().getId().equals(usuarioUtils.getUsuarioLogado().getId()) &&
                                    exercicioUsuario.isConcluido())
                            .count();

                    return new FaseResultDto(fase.getId(), fase.getNumFase(),
                            fase.getTitulo(), fase.getExercicios().size(), (int) qtdExerciciosFaseConcluidos, b);
                }).toList();
        return ResponseEntity.status(200).body(dtos);
    }
}
