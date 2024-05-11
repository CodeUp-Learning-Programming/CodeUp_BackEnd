package up.code.codeup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import up.code.codeup.entity.Amizade;
import up.code.codeup.entity.Usuario;
import up.code.codeup.exception.EntidadeNaoEncontradaException;
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


    public boolean solicitarAmizade(Integer idSolicitante, String emailReceptor) {
        boolean conviteEnviado = false;
        Optional<Usuario> optSolicitante = usuarioRepository.findById(idSolicitante);
        Optional<Usuario> optReceptor = usuarioRepository.findByEmail(emailReceptor);

        if (optSolicitante.isPresent() && optReceptor.isPresent()) {
            Usuario solicitante = optSolicitante.get();
            Usuario receptor = optReceptor.get();
            Optional<Amizade> optAmizadeExistente = amizadeRepository.buscarAmizadeExistente(solicitante.getId(), receptor.getId());
            if (optAmizadeExistente.isEmpty()) {
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

    public List<Amizade> buscarSolicitacoesAmizade(Integer idUsuario) {
        List<Amizade> amizades = amizadeRepository.buscarSolicitacoesAmizade(idUsuario);
        return amizades;
    }

    public List<Amizade> buscarSolicitacoesAmizadeEnviadas(Integer idUsuario) {
        List<Amizade> amizades = amizadeRepository.buscarSolicitacoesAmizadeEnviadas(idUsuario);
        return amizades;
    }

    public List<Amizade> buscarAmigos(Integer idUsuario) {
        List<Amizade> amizades = amizadeRepository.buscarAmigos(idUsuario);
        return amizades;
    }

    public StatusPedidoAmizade gerenciarPedido(String emailSolicitante, Integer idReceptor, boolean resposta) {
        Optional<Usuario> optUsuario = usuarioRepository.findByEmail(emailSolicitante);
        Integer idUsuarioSolicitante = -1;
        if (optUsuario.isPresent()){
            idUsuarioSolicitante = optUsuario.get().getId();
        }

        System.out.println("id solicitante " + idUsuarioSolicitante);
        System.out.println("id receptor " + idReceptor);
        Optional<Amizade> solicitacao = amizadeRepository.buscarAmizadeExistente(idUsuarioSolicitante, idReceptor);

        System.out.println("Aqui ó este caralho: " + solicitacao.get());

        if (!solicitacao.isEmpty()) {
            Amizade amizade = solicitacao.get();
            if (resposta == true) {
                amizade.setStatus(StatusPedidoAmizade.ACEITO);
            } else {
                amizade.setStatus(StatusPedidoAmizade.RECUSADO);
            }
            amizadeRepository.save(amizade);
            return amizade.getStatus();
        } else {
            throw new EntidadeNaoEncontradaException("Solicitação de amizade não encontrada");
        }
    }
}
