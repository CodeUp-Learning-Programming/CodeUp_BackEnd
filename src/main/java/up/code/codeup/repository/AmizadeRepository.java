package up.code.codeup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import up.code.codeup.entity.Amizade;
import up.code.codeup.entity.Exercicio;
import up.code.codeup.entity.Usuario;

import java.util.List;
import java.util.Optional;
@Repository
public interface AmizadeRepository extends JpaRepository<Amizade, Integer> {
    @Query("SELECT a FROM Amizade a WHERE a.solicitante.id = :idSolicitante AND a.receptor.id = :idReceptor")
    Optional<Amizade> buscarAmizadeExistente(Integer idSolicitante, Integer idReceptor);

    @Query("SELECT a FROM Amizade a WHERE a.receptor.id = :idReceptor AND a.status = 0")
    List<Amizade> buscarSolicitacoesAmizade(Integer idReceptor);

    @Query("SELECT a FROM Amizade a WHERE a.solicitante.id = :idSolicitante AND a.status = 0")
    List<Amizade> buscarSolicitacoesAmizadeEnviadas(Integer idSolicitante);

    @Query("SELECT a FROM Amizade a WHERE (a.solicitante.id = :idUsuario OR a.receptor.id = :idUsuario) AND a.status = 1")
    List<Amizade> buscarAmigos(Integer idUsuario);
}

