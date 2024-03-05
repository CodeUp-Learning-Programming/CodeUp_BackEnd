package up.code.codeup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import up.code.codeup.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmailOrNome(String email, String nome);

    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByNome(String nome);

    Optional<Usuario> findByNomeIgnoreCase(String nome);

    int countByNomeContainsIgnoreCase(String nome);
    @Modifying
    @Transactional
    @Query("update Usuario u set u.fotoPerfil = ?2 where u.id = ?1")
    void setFoto(Integer id, String foto);

//    @Query("select p.foto from Planta p where p.id = ?1")
//    byte[] getFoto(Integer id);
//
//    @Modifying
//    @Transactional
//    @Query("update Planta p set p.relatorioExcel = ?2 where p.id = ?1")
//    void setRelatorio(Integer id, byte[] foto);
//
//    @Query("select p.relatorioExcel from Planta p where p.id = ?1")
//    byte[] getRelatorio(Integer id);

}
