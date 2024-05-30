package up.code.codeup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.code.codeup.dto.ImageDto;
import up.code.codeup.dto.usuarioDto.*;
import up.code.codeup.entity.Usuario;
import up.code.codeup.mapper.UsuarioMapper;
import up.code.codeup.service.ExercicioUsuarioService;
import up.code.codeup.service.UsuarioService;
import up.code.codeup.utils.UsuarioUtils;

import java.util.Map;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {
    private UsuarioService service;
    private ExercicioUsuarioService exercicioUsuarioService;
    private UsuarioUtils usuarioUtils;

    public UsuarioController(UsuarioService service, ExercicioUsuarioService exercicioUsuarioService, UsuarioUtils usuarioUtils) {
        this.service = service;
        this.exercicioUsuarioService = exercicioUsuarioService;
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
        System.out.println(usuarioToken.getNome());
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

    @GetMapping("/exercicios/mes/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Map<Integer, Integer>> exerciciosConcluidosMes(@PathVariable @NotNull Integer id) {
        Map<Integer, Integer> mesQtdExercicio = exercicioUsuarioService.exerciciosConcluidosMes(id);
        return ResponseEntity.ok(mesQtdExercicio);
    }

}
