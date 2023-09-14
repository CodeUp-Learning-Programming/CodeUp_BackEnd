package up.code.codeup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.code.codeup.entity.usuario.Usuario;
import up.code.codeup.entity.usuario.UsuarioLoginDTO;
import up.code.codeup.service.UsuarioService;
import up.code.codeup.service.usuario.autenticacao.dto.UsuarioTokenDto;
import up.code.codeup.service.usuario.dto.UsuarioCriacaoDto;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.buscarUsuarios();
        return ResponseEntity.status(200).body(usuarios);
    }

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> criar(@RequestBody @Valid UsuarioCriacaoDto usuarioCriacaoDto) {
        this.usuarioService.criar(usuarioCriacaoDto);
        return ResponseEntity.status(201).build();
    }

//    @PostMapping
//    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody UsuarioCriacaoDto novoUsuario) {
//        if (novoUsuario != null) {
//            return ResponseEntity.status(200).body(usuarioService.criar(novoUsuario));
//        }
//        return ResponseEntity.status(400).build();
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable int id, @RequestBody Usuario usuarioAtualizado) {
        if (usuarioService.atualizarUsuario(usuarioAtualizado, id) != null) {
            return ResponseEntity.status(200).body(usuarioAtualizado);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deletarUsuario(@PathVariable int id) {
        if (usuarioService.deletarUsuario(id)) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable int id) {
        if (usuarioService.buscarUsuarioPorId(id) != null) {
            return ResponseEntity.status(200).body(usuarioService.buscarUsuarioPorId(id));
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDTO usuarioLoginDTO) {
        UsuarioTokenDto usuarioToken = this.usuarioService.autenticar(usuarioLoginDTO);
        return ResponseEntity.status(200).body(usuarioToken);
    }
}
