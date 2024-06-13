package up.code.codeup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import up.code.codeup.dto.amizadeDto.BuscarPorNomeResultDto;
import up.code.codeup.entity.Amizade;
import up.code.codeup.entity.Usuario;
import up.code.codeup.exception.EntidadeNaoEncontradaException;
import up.code.codeup.mapper.AmizadeMapper;
import up.code.codeup.repository.AmizadeRepository;
import up.code.codeup.repository.UsuarioRepository;
import up.code.codeup.utils.StatusPedidoAmizade;

import java.util.*;

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
            ArrayList<Integer> ids = new ArrayList<>();
            ids.add(idSolicitante);
            ids.add(receptor.getId());
            List<Amizade> optAmizadeExistente = amizadeRepository.buscarRelacionamento(ids);
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
        ArrayList<Integer> ids = new ArrayList<>();
        if (optUsuario.isPresent()) {
            Usuario usuarioSolicitante = optUsuario.get();
            ids.add(usuarioSolicitante.getId());
        }
        ids.add(idReceptor);

        List<Amizade> solicitacao = amizadeRepository.buscarRelacionamento(ids);

        if (!solicitacao.isEmpty()) {
            Amizade amizade = solicitacao.get(0);
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

    public List<BuscarPorNomeResultDto> buscarRelacionamentoPorNome(String nome, Integer usuarioLogadoID) {
        ArrayList<BuscarPorNomeResultDto> returnList = new ArrayList<BuscarPorNomeResultDto>();

        List<Usuario> usuarios = usuarioRepository.buscarPorNome(nome, usuarioLogadoID);
        List<Amizade> amizades = amizadeRepository.buscarAmizadesPorIdUsuario(usuarioLogadoID);

        Set<Usuario> usuariosProcessados = new HashSet<>();

        for (Usuario usuario : usuarios) {
            if (usuariosProcessados.contains(usuario)) {
                continue;
            }

            BuscarPorNomeResultDto buscarPorNomeResultDto = null;

            if (amizades.isEmpty()) {
                buscarPorNomeResultDto = AmizadeMapper.toBuscarPorNomeResultDto(usuarioLogadoID, usuario);
            } else {
                boolean isAmigo = false;
                for (Amizade amizade : amizades) {
                    if (amizade.getSolicitante().getId() == usuario.getId() || amizade.getReceptor().getId() == usuario.getId()) {
                        buscarPorNomeResultDto = AmizadeMapper.toBuscarPorNomeResultDto(amizade, usuarioLogadoID, usuario);
                        isAmigo = true;
                        break;
                    }
                }
                if (!isAmigo) {
                    buscarPorNomeResultDto = AmizadeMapper.toBuscarPorNomeResultDto(usuarioLogadoID, usuario);
                }
            }

            returnList.add(buscarPorNomeResultDto);
            usuariosProcessados.add(usuario);
        }


        System.out.println("Tamanaho da lista" + returnList.size());

        return returnList;
    }
}
