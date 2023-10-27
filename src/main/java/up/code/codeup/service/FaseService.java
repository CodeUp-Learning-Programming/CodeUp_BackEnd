package up.code.codeup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.code.codeup.entity.Fase;
import up.code.codeup.exception.EntidadeNaoEncontradaException;
import up.code.codeup.repository.FaseRepository;
import up.code.codeup.repository.MateriaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaseService {

    private final FaseRepository faseRepository;
    private final MateriaRepository materiaRepository;

    public List<Fase> buscarFases() {
        return faseRepository.findAll();
    }

    public Fase buscarFasePorId(int id) {
        Fase fase = faseRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Fase")
        );
        return fase;
    }
}
