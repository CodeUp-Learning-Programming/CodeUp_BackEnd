package up.code.codeup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.code.codeup.dto.exercicioDto.ExercicioCriacaoDTO;
import up.code.codeup.dto.exercicioDto.ExercicioDTO;
import up.code.codeup.entity.Exercicio;
import up.code.codeup.entity.Fase;
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

    public List<ExercicioDTO> buscarFases() {
        List<Exercicio> listaExercicio = this.exercicioRepository.findAll();
        List<ExercicioDTO> listaExercicioDTO = new ArrayList<>();

        for(int i = 0; i < listaExercicio.size();i++){
            listaExercicioDTO.add(ExercicioMapper.paraDTO(listaExercicio.get(i)));
        }

        return listaExercicioDTO;
    }

    public Exercicio criar(ExercicioCriacaoDTO exercicioCriacaoDTO) {
        final Exercicio novoExercicio = ExercicioMapper.paraEntidade(exercicioCriacaoDTO);
        Fase fase = faseRepository.findById(exercicioCriacaoDTO.getFase().getId()).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Fase")
        );
        novoExercicio.setFase(fase);
        this.exercicioRepository.save(novoExercicio);
        return novoExercicio;
    }

    public Exercicio atualizarExercicio(Exercicio novaExercicio, int id) {
        exercicioRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Exercicio")
        );
        novaExercicio.setId(id);
        return exercicioRepository.save(novaExercicio);

    }

    public boolean deletarExercicio(int id) {
        Exercicio fase = exercicioRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Exercicio")
        );

        exercicioRepository.delete(fase);
        return true;
    }

    public Exercicio buscarExercicioPorId(int id) {
        Exercicio fase = exercicioRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Exercicio")
        );
        return fase;
    }

    public ExercicioDTO buscarExercicio(Integer fk_fase, Integer numExercicio) {
        Exercicio exercicio = this.exercicioRepository.buscarExercicioPorNumero(fk_fase, numExercicio).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Exercicio")
        );

        return ExercicioMapper.paraDTO(exercicio);
    }

    public List<ExercicioDTO> buscarExercicioPorNumExercicio(Integer fk_fase) {
        List<Exercicio> listaExercicio = this.exercicioRepository.findByFase(fk_fase);
        List<ExercicioDTO> listaExercicioDTO = new ArrayList<>();

        for(int i = 0; i < listaExercicio.size();i++){
            listaExercicioDTO.add(ExercicioMapper.paraDTO(listaExercicio.get(i)));
        }

        return listaExercicioDTO;
    }

}
