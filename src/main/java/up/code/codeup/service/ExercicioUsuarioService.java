package up.code.codeup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.code.codeup.repository.ExerciciosUsuarioRepository;

@Service
@RequiredArgsConstructor
public class ExercicioUsuarioService {
    private final ExerciciosUsuarioRepository repository;

    public boolean verificarExercicioConcluido(int idExercicio, int idUsuario) {
        return repository.existsByExercicioIdAndUsuarioIdAndConcluidoTrue(idExercicio, idUsuario);
    }
}
