package up.code.codeup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.code.codeup.entity.Fase;
import up.code.codeup.entity.view.ViewFaseResult;
import up.code.codeup.repository.FaseRepository;
import up.code.codeup.repository.ViewFaseResultRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FaseService {
    private final FaseRepository repository;
    private final ViewFaseResultRepository viewFaseResultRepository;

    public List<Fase> buscarFasesPorIdMateria(int idMateria) {
        return repository.findByMateriaId(idMateria)
                .stream()
                .collect(Collectors.toList());
    }

    public List<ViewFaseResult> buscarFaseResultPorIdMateriaIdUsuario(int idMateria, int idUsuario) {
        return viewFaseResultRepository.findByMateriaIdAndUsuarioIdAndConcluidoTrue(idMateria, idUsuario);
    }
}
