package up.code.codeup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.code.codeup.entity.Fase;
import up.code.codeup.repository.FaseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaseService {
    private final FaseRepository repository;

    public List<Fase> buscarFasesPorIdMateria(int idMateria) {
        return repository.findByMateriaId(idMateria);
    }

}
