package up.code.codeup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import up.code.codeup.entity.ExercicioUsuario;
import up.code.codeup.entity.Usuario;
import up.code.codeup.repository.ExercicioUsuarioRepository;
import up.code.codeup.repository.UsuarioRepository;
import up.code.codeup.utils.UsuarioUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExercicioUsuarioService {
    private final ExercicioUsuarioRepository repository;
    private final UsuarioRepository usuarioRepository;

    public void concluiuExercicio(int idExercicio, int idFase, Usuario usuario) {
        Optional<ExercicioUsuario> exercicioUsuario = repository.findByUsuarioIdAndExercicioId(usuario.getId(), idExercicio);

        if (exercicioUsuario.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercício não encontrado");
        } else {
            ExercicioUsuario exercicio = exercicioUsuario.get();
            exercicio.setConcluido(true);
            usuario.setMoedas(usuario.getMoedas() + exercicio.getExercicio().getMoeda());
            usuario.setXp(usuario.getXp() + exercicio.getExercicio().getXp());
            usuarioRepository.save(usuario);
            repository.save(exercicio);
        }
        repository.atualizarFaseDesbloqueadaParaUsuario(usuario.getId(), idFase);
    }
}
