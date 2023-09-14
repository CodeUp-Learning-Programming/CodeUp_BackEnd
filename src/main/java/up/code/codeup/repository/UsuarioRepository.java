package up.code.codeup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import up.code.codeup.entity.usuario.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String username);

//    Object getReferenceByEmail(String email);
//    Usuario findByEmail(String email);
}
