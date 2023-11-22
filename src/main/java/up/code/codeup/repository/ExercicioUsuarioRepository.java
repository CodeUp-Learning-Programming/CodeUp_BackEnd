package up.code.codeup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface ExercicioUsuarioRepository extends JpaRepository<ExercicioUsuarioRepository, Integer> {
    @Procedure(name = "atualizarFaseDesbloqueadaParaUsuario")
    Void atualizarFaseDesbloqueadaParaUsuario(@Param("usuario_id") Integer usuarioId, @Param("fase_atual") Integer faseAtual);
}
