package up.code.codeup.service;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import up.code.codeup.entity.Amizade;
import up.code.codeup.entity.Usuario;
import up.code.codeup.repository.AmizadeRepository;
import up.code.codeup.repository.UsuarioRepository;
import up.code.codeup.utils.StatusPedidoAmizade;

import java.util.List;
import java.util.Optional;
@Service
public class AmizadeService {
    @Autowired
    private AmizadeRepository amizadeRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;


    public boolean solicitarAmizade(Integer idSolicitante, String emailReceptor){
        boolean conviteEnviado = false;
        Optional<Usuario> optSolicitante = usuarioRepository.findById(idSolicitante);
        Optional<Usuario> optReceptor = usuarioRepository.findByEmail(emailReceptor);

        if (optSolicitante.isPresent() && optReceptor.isPresent()){
            Usuario solicitante = optSolicitante.get();
            Usuario receptor = optReceptor.get();
            Optional<Amizade> optAmizadeExistente = amizadeRepository.buscarAmizadeExistente(solicitante.getId(), receptor.getId());
            if(optAmizadeExistente.isEmpty()){
                Amizade amizade = new Amizade();
                amizade.setReceptor(receptor);
                amizade.setSolicitante(solicitante);
                amizade.setStatus(StatusPedidoAmizade.PENDENTE);
                amizadeRepository.save(amizade);
                conviteEnviado = true;
            }
        }
        return conviteEnviado;
    }

    public List<Amizade> buscarSolicitacoesAmizade(Integer idUsuario){
        List<Amizade> amizades = amizadeRepository.buscarSolicitacoesAmizade(idUsuario);
        return amizades;
    }
}
