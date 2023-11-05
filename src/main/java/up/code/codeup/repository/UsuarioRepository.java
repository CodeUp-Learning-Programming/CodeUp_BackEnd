package up.code.codeup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import up.code.codeup.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmailOrNome(String email, String nome);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByNomeIgnoreCase(String nome);

    int countByNomeContainsIgnoreCase(String nome);
}
