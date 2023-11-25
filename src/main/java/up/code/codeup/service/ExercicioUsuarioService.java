package up.code.codeup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import up.code.codeup.entity.ExercicioUsuario;
import up.code.codeup.repository.ExercicioUsuarioRepository;
import up.code.codeup.utils.UsuarioUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExercicioUsuarioService {
    private final ExercicioUsuarioRepository repository;
    private final UsuarioUtils usuarioUtils;

    public void concluiuExercicio(int idExercicio) {
        Optional<ExercicioUsuario> exercicio = repository.findByUsuarioIdAndExercicioId(idExercicio, usuarioUtils.getUsuarioLogado().getId());

        if (exercicio.isPresent()) {
            exercicio.get().setConcluido(true);
            repository.save(exercicio.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercício não encontrado");
        }

//        repository.atualizarFaseDesbloqueadaParaUsuario(usuarioUtils.getUsuarioLogado().getId());
    }
}
