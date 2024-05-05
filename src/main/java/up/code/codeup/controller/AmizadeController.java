package up.code.codeup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.code.codeup.dto.usuarioDto.AmizadeResultDto;
import up.code.codeup.dto.usuarioDto.SolicitarAmizadeRequest;
import up.code.codeup.entity.Amizade;
import up.code.codeup.entity.Usuario;
import up.code.codeup.mapper.AmizadeMapper;
import up.code.codeup.service.AmizadeService;
import up.code.codeup.service.UsuarioService;
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
}
