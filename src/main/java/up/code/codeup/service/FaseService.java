package up.code.codeup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import up.code.codeup.dto.faseDto.FaseCriacaoDTO;
import up.code.codeup.dto.faseDto.FaseDTO;
import up.code.codeup.entity.Fase;
import up.code.codeup.entity.Materia;
import up.code.codeup.exception.EntidadeNaoEncontradaException;
import up.code.codeup.mapper.FaseMapper;
import up.code.codeup.repository.FaseRepository;
import up.code.codeup.repository.MateriaRepository;

import java.util.List;
import java.util.Optional;

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
