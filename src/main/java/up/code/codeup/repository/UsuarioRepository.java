package up.code.codeup.repository;

import org.springframework.data.repository.CrudRepository;
import up.code.codeup.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

}
