package up.code.codeup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import up.code.codeup.entity.materia.Materia;
import up.code.codeup.mapper.MateriaMapper;
import up.code.codeup.repository.MateriaRepository;
import up.code.codeup.service.materia.dto.MateriaCriacaoDto;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    public List<Materia> buscarMaterias() {
        return materiaRepository.findAll();
    }

    public void criar(MateriaCriacaoDto materiaCriacaoDto) {
        final Materia novaMateria = MateriaMapper.of(materiaCriacaoDto);
        this.materiaRepository.save(novaMateria);
    }

    public Materia atualizarMateria(Materia novaMateria, int id) {
        Optional<Materia> materia = materiaRepository.findById(id);
        if (materia.isPresent()) {
            Materia materiaExistente = materia.get();
            materiaExistente.setNome(novaMateria.getNome());
            materiaRepository.save(materiaExistente);
            return materiaExistente;
        }
        return null;
    }

    public boolean deletarMateria(int id) {
        Optional<Materia> materia = materiaRepository.findById(id);
        materia.ifPresent(u -> materiaRepository.delete(u));
        return true;
    }

    public Materia buscarMateriaPorId(int id) {
        Optional<Materia> materia = materiaRepository.findById(id);
        if (materia.isPresent()) {
            Materia materiaExistente = materia.get();
            return materiaExistente;
        }
        return null;
    }

}
