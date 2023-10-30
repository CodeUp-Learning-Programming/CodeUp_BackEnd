package up.code.codeup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.code.codeup.dto.exercicioDto.ExercicioResponseDto;
import up.code.codeup.dto.faseDto.FaseExercicioResponseDto;
import up.code.codeup.dto.materiaDto.MateriaFaseResponseDto;
import up.code.codeup.entity.Fase;
import up.code.codeup.entity.Materia;
import up.code.codeup.exception.EntidadeNaoEncontradaException;
import up.code.codeup.mapper.FaseMapper;
import up.code.codeup.mapper.MateriaMapper;
import up.code.codeup.repository.FaseRepository;
import up.code.codeup.repository.MateriaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FaseService {

    private final FaseRepository faseRepository;
    private final MateriaRepository materiaRepository;

    public List<Fase> buscarFases() {
        return faseRepository.findAll();
    }

    public FaseExercicioResponseDto buscarFaseExerciciosPorId(int id) {
        Fase fase = faseRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Fase")
        );
        FaseExercicioResponseDto faseExercicioDto = FaseMapper.paraFaseExercicioResponseDto(fase);

        return faseExercicioDto;
    }

    public List<FaseExercicioResponseDto> buscarFaseExercicios() {
        List<Fase> fases = faseRepository.findAll();
        List<FaseExercicioResponseDto> faseExercicioDto = new ArrayList<>();
        for (Fase fase: fases) {
            faseExercicioDto.add(FaseMapper.paraFaseExercicioResponseDto(fase));
        }
        return faseExercicioDto;
    }
}
