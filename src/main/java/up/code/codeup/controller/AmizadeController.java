package up.code.codeup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.code.codeup.dto.amizadeDto.*;
import up.code.codeup.entity.Amizade;
import up.code.codeup.entity.Usuario;
import up.code.codeup.mapper.AmizadeMapper;
import up.code.codeup.service.AmizadeService;
import up.code.codeup.service.UsuarioService;
import up.code.codeup.utils.StatusPedidoAmizade;
import up.code.codeup.utils.UsuarioUtils;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/amizades")
public class AmizadeController {
    private final AmizadeService amizadeService;
    private final UsuarioService usuarioService;
    private final UsuarioUtils usuarioUtils;

    public AmizadeController(AmizadeService amizadeService, UsuarioService usuarioService, UsuarioUtils usuarioUtils) {
        this.amizadeService = amizadeService;
        this.usuarioService = usuarioService;
        this.usuarioUtils = usuarioUtils;
    }


    @PostMapping("/solicitar/amizade")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Boolean> solicitarAmizade(@RequestBody SolicitarAmizadeRequest body) {
        Usuario usuario = usuarioService.buscarPorEmail(body.getEmailReceptor());
        if(body.getIdSolicitante() == usuario.getId()){
            return ResponseEntity.status(400).build();
        }
        Boolean conviteEnviado = amizadeService.solicitarAmizade(body.getIdSolicitante(), body.getEmailReceptor());
        return ResponseEntity.status(200).body(conviteEnviado);
    }

    @GetMapping("/solicitacoes/recebidas")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<AmizadeResultDto>> solicitacoesAmizadeRecebidas(@RequestParam Integer idUsuario) {
        List<Amizade> amizades = amizadeService.buscarSolicitacoesAmizade(idUsuario);

        ArrayList<AmizadeResultDto> amizadesDto = new ArrayList<>();
        for (Amizade a: amizades) {
            Usuario usuario = usuarioService.buscarPorId(a.getSolicitante().getId());
            amizadesDto.add(AmizadeMapper.toDto(a, usuario));
        }
        return ResponseEntity.status(200).body(amizadesDto);
    }

    @GetMapping("/solicitacoes/enviadas")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<AmizadeResultDto>> solicitacoesAmizadeEnviadas(@RequestParam Integer idUsuario) {
        List<Amizade> amizades = amizadeService.buscarSolicitacoesAmizadeEnviadas(idUsuario);

        ArrayList<AmizadeResultDto> amizadesDto = new ArrayList<>();
        for (Amizade a: amizades) {
            Usuario usuario = usuarioService.buscarPorId(a.getReceptor().getId());
            amizadesDto.add(AmizadeMapper.toDto(a, usuario));
        }
        return ResponseEntity.status(200).body(amizadesDto);
    }

    @GetMapping("/amigos")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<AmigoDto>> amigos(@RequestParam Integer idUsuario) {
        List<Amizade> amizades = amizadeService.buscarAmigos(idUsuario);

        ArrayList<AmigoDto> amizadesDto = new ArrayList<>();
        for (Amizade a: amizades) {
            Integer idAmigo = 0;
            if(a.getSolicitante().getId() == idUsuario){
                idAmigo = a.getReceptor().getId();
            }else {
                idAmigo = a.getSolicitante().getId();
            }
            Usuario amigo = usuarioService.buscarPorId(idAmigo);
            amizadesDto.add(AmizadeMapper.toAmigoDto(amigo));
        }
        return ResponseEntity.status(200).body(amizadesDto);
    }

    @PostMapping("/gerenciar/convite")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<StatusPedidoAmizade> gerenciarConvite(@RequestBody RespostaSolicitacao res) {
        StatusPedidoAmizade statusPedidoAmizade = amizadeService.gerenciarPedido(res.getEmailSolicitante(), res.getIdReceptor(), res.isResposta());
        return ResponseEntity.status(200).body(statusPedidoAmizade);
    }

    @PostMapping("/buscar_por_nome")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<BuscarPorNomeResultDto>> buscarRelacionemtno(@RequestBody BuscarPorNomeDto res) {
        List<BuscarPorNomeResultDto> buscarPorNomeResultDto = amizadeService.buscarRelacionamentoPorNome(res.getNome(), res.getIdUsuario());
        return ResponseEntity.status(200).body(buscarPorNomeResultDto);
    }
}
