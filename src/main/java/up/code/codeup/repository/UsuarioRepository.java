package up.code.codeup.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestBody;
import up.code.codeup.entity.Usuario;

import java.util.List;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    //Query de login
    @Query("SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha")
    Usuario login(String email, String senha);

    @Query("SELECT u FROM Usuario u")
    List<Usuario> listarUsuarios();
}
