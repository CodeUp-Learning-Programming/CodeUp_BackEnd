package up.code.codeup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.code.codeup.dto.exercicioDto.ExercicioResponseDto;
import up.code.codeup.entity.Exercicio;
import up.code.codeup.exception.EntidadeNaoEncontradaException;
import up.code.codeup.repository.ExercicioRepository;
import up.code.codeup.repository.FaseRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ExercicioService {

    private final ExercicioRepository repository;
    private final FaseRepository faseRepository;

    public List<Exercicio> buscarExerciciosPorIdFase(int idFase) {
        return repository.findByFaseId(idFase);
    }

}
