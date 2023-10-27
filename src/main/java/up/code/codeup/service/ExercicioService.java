package up.code.codeup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.code.codeup.dto.exercicioDto.ExercicioResponseDto;
import up.code.codeup.entity.Exercicio;
import up.code.codeup.exception.EntidadeNaoEncontradaException;
import up.code.codeup.mapper.ExercicioMapper;
import up.code.codeup.repository.ExercicioRepository;
import up.code.codeup.repository.FaseRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ExercicioService {

    private final ExercicioRepository exercicioRepository;
    private final FaseRepository faseRepository;

    public List<ExercicioResponseDto> buscarFases() {
        List<Exercicio> listaExercicio = this.exercicioRepository.findAll();
        List<ExercicioResponseDto> listaExercicioResponseDto = new ArrayList<>();

        for(int i = 0; i < listaExercicio.size();i++){
            listaExercicioResponseDto.add(ExercicioMapper.paraDTO(listaExercicio.get(i)));
        }

        return listaExercicioResponseDto;
    }

    public Exercicio buscarExercicioPorId(int id) {
        Exercicio fase = exercicioRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Exercicio")
        );
        return fase;
    }

    public ExercicioResponseDto buscarExercicio(Integer fk_fase, Integer numExercicio) {
        Exercicio exercicio = this.exercicioRepository.buscarExercicioPorNumero(fk_fase, numExercicio).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Exercicio")
        );

        return ExercicioMapper.paraDTO(exercicio);
    }

    public List<ExercicioResponseDto> buscarExercicioPorNumExercicio(Integer fk_fase) {
        List<Exercicio> listaExercicio = this.exercicioRepository.findByFase(fk_fase);
        List<ExercicioResponseDto> listaExercicioResponseDto = new ArrayList<>();

        for(int i = 0; i < listaExercicio.size();i++){
            listaExercicioResponseDto.add(ExercicioMapper.paraDTO(listaExercicio.get(i)));
        }

        return listaExercicioResponseDto;
    }

}
