package up.code.codeup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import up.code.codeup.entity.usuario.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    UsuarioEntity findByEmail(String email);
}
