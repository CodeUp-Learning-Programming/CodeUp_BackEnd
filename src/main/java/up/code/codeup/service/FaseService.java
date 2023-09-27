package up.code.codeup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import up.code.codeup.entity.fase.Fase;
import up.code.codeup.entity.materia.Materia;
import up.code.codeup.repository.FaseRepository;
import up.code.codeup.repository.MateriaRepository;
import up.code.codeup.service.fase.dto.FaseCriacaoDto;
import up.code.codeup.service.fase.dto.FaseMapper;

import java.util.List;
import java.util.Optional;

@Service
public class FaseService {

    @Autowired
    private FaseRepository faseRepository;
    @Autowired
    private MateriaRepository materiaRepository;

    public List<Fase> buscarFases() {
        return faseRepository.findAll();
    }

    public void criar(FaseCriacaoDto faseCriacaoDto) {
        final Fase novaFase = FaseMapper.of(faseCriacaoDto);
        Optional<Materia> optMateria = materiaRepository.findById(faseCriacaoDto.getMateria().getId_materia());
        if (optMateria.isPresent()) {
            novaFase.setMateria(optMateria.get());
            this.faseRepository.save(novaFase);
        }
    }

    public Fase atualizarFase(Fase novaFase, int id) {
        Optional<Fase> fase = faseRepository.findById(id);
        if (fase.isPresent()) {
            Fase faseExistente = fase.get();
            faseExistente.setNome(novaFase.getNome());
            faseRepository.save(faseExistente);
            return faseExistente;
        }
        return null;
    }

    public boolean deletarFase(int id) {
        Optional<Fase> fase = faseRepository.findById(id);
        fase.ifPresent(u -> faseRepository.delete(u));
        return true;
    }

    public Fase buscarFasePorId(int id) {
        Optional<Fase> fase = faseRepository.findById(id);
        if (fase.isPresent()) {
            Fase faseExistente = fase.get();
            return faseExistente;
        }
        return null;
    }
}
