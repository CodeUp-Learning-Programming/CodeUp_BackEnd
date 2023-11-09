package up.code.codeup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.code.codeup.dto.faseDto.FaseExercicioResponseDto;
import up.code.codeup.entity.ExerciciosUsuario;
import up.code.codeup.entity.Fase;
import up.code.codeup.repository.ExercicioRepository;
import up.code.codeup.repository.ExerciciosUsuarioRepository;
import up.code.codeup.repository.FaseRepository;
import up.code.codeup.utils.UsuarioUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FaseService {
    private final FaseRepository repository;
    private final ExerciciosUsuarioRepository exerciciosUsuarioRepository;
    private final ExercicioRepository exercicioRepository;

    public List<Fase> buscarFasesPorIdMateria(int idMateria) {
        return repository.findByMateriaId(idMateria)
                .stream()
                .collect(Collectors.toList());
    }

}
