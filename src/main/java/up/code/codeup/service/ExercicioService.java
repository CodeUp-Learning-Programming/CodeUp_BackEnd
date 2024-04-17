package up.code.codeup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.code.codeup.entity.Exercicio;
import up.code.codeup.repository.ExercicioRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExercicioService {

    private final ExercicioRepository repository;

    public List<Exercicio> buscarExerciciosPorIdFase(int idFase) {
        return repository.findByFaseId(idFase);
    }
}
