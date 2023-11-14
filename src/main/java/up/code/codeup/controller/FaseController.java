package up.code.codeup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.code.codeup.entity.view.ViewFaseResult;
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
    public ResponseEntity<List<ViewFaseResult>> buscarFaseResultPorIdMateriaIdUsuario(@PathVariable @NotNull Integer idMateria) {
        List<ViewFaseResult> listFaseResults = service.buscarFaseResultPorIdMateriaIdUsuario(idMateria, UsuarioUtils.getUsuarioLogado().getId());

        if (listFaseResults.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(listFaseResults);
    }
}
