package up.code.codeup.controller;

import com.oracle.truffle.regex.charset.SortedListOfRanges;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import up.code.codeup.dto.ImageDto;
import up.code.codeup.dto.usuarioDto.*;
import up.code.codeup.entity.Amizade;
import up.code.codeup.entity.Usuario;
import up.code.codeup.mapper.AmizadeMapper;
import up.code.codeup.mapper.UsuarioMapper;
import up.code.codeup.service.UsuarioService;
import up.code.codeup.utils.UsuarioUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {
    private UsuarioService service;
    private UsuarioUtils usuarioUtils;

    public UsuarioController(UsuarioService service, UsuarioUtils usuarioUtils) {
        this.service = service;
        this.usuarioUtils = usuarioUtils;
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<UsuarioDetalhesPerfil> buscarPorId(@PathVariable @NotNull Integer id) {
        Usuario usuario = service.buscarPorId(id);
        new UsuarioDetalhesPerfil(usuario);
        return ResponseEntity.status(200).body(new UsuarioDetalhesPerfil(usuario));
    }

    @GetMapping("/atualizar/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<UsuarioDetalhesPerfil> atualizarListaItensPorId(@PathVariable @NotNull Integer id) {
        Usuario usuario = service.buscarPorId(id);
        new UsuarioAtualizado(usuario);
        return ResponseEntity.status(200).body(new UsuarioDetalhesPerfil(usuario));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDetalhesCriacaoDto> cadastrar(@RequestBody @Valid UsuarioCriacaoDto usuarioCriacaoDto) {
        Usuario novoUsuario = UsuarioMapper.of(usuarioCriacaoDto);
        return ResponseEntity.status(201).body(new UsuarioDetalhesCriacaoDto(service.cadastrar(novoUsuario)));
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDTO usuarioLoginDTO) {
        UsuarioTokenDto usuarioToken = this.service.autenticar(usuarioLoginDTO);
        return ResponseEntity.status(200).body(usuarioToken);
    }

    @PatchMapping("/foto")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> atualizarFotoPerfil(@RequestBody @NotNull ImageDto novaFoto) {
        service.atualizarFotoPerfil(novaFoto.getImage());
        System.out.println("Aqui ó: " + novaFoto.getImage());
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/foto")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> removerFotoPerfil() {
        service.removerFotoPerfil();
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/perfil")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<String> removerPerfil(@RequestBody String senha) {
        service.removerPerfil(senha);
        return ResponseEntity.status(200).body("Perfil removido com sucesso!");
    }

    @PostMapping("/solicitar/amizade")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Boolean> solicitarAmizade(@RequestBody SolicitarAmizadeRequest body) {
        Boolean conviteEnviado = service.solicitarAmizade(body.getIdSolicitante(), body.getEmailReceptor());
        return ResponseEntity.status(200).body(conviteEnviado);
    }

    @GetMapping("/solicitacoes/recebidas")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<AmizadeResultDto>> solicitacoesAmizadeRecebidas(@RequestParam Integer idUsuario) {
        List<Amizade> amizades = service.buscarSolicitacoesAmizade(idUsuario);

        ArrayList<AmizadeResultDto> amizadesDto = new ArrayList<>();
        for (Amizade a: amizades) {
            Usuario usuario = service.buscarPorId(a.getSolicitante().getId());
            amizadesDto.add(AmizadeMapper.toDto(a, usuario));
        }
        return ResponseEntity.status(200).body(amizadesDto);
    }


}
