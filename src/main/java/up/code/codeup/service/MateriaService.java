package up.code.codeup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import up.code.codeup.entity.Materia;
import up.code.codeup.exception.EntidadeNaoEncontradaException;
import up.code.codeup.repository.MateriaRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MateriaService {

    @Autowired
    private MateriaRepository repository;

    public List<Materia> buscarMaterias() {
        return repository.findAll();
    }

    public Materia buscarMateriaPorId(int id) {
        Optional<Materia> materia = repository.findById(id);
        if (materia.isPresent()) {
            return materia.get();
        }
        throw new EntidadeNaoEncontradaException("Id inválido");
    }

}
