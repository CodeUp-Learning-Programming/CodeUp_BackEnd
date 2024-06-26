package up.code.codeup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.code.codeup.entity.Fase;
import up.code.codeup.repository.FaseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FaseService {
    private final FaseRepository repository;

    public List<Fase> buscarFasesPorIdMateria(int idMateria) {
        return repository.findByMateriaId(idMateria)
                .stream()
                .collect(Collectors.toList());
    }
}
