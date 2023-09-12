package up.code.codeup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.code.codeup.entity.usuario.UsuarioEntity;
import up.code.codeup.entity.usuario.UsuarioLoginDTO;
import up.code.codeup.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> listarUsuarios() {
        List<UsuarioEntity> usuarios = usuarioService.buscarUsuarios();
        return ResponseEntity.status(200).body(usuarios);
    }

    @PostMapping
    public ResponseEntity<UsuarioEntity> cadastrarUsuario(@RequestBody UsuarioEntity novoUsuario) {
        if (novoUsuario != null) {
            return ResponseEntity.status(200).body(usuarioService.cadastrarUsuario(novoUsuario));
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> atualizarUsuario(@PathVariable int id, @RequestBody UsuarioEntity usuarioAtualizado) {
        if (usuarioService.atualizarUsuario(usuarioAtualizado, id) != null) {
            return ResponseEntity.status(200).body(usuarioAtualizado);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioEntity> deletarUsuario(@PathVariable int id) {
        if (usuarioService.deletarUsuario(id)) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> buscarUsuarioPorId(@PathVariable int id) {
        if (usuarioService.buscarUsuarioPorId(id) != null) {
            return ResponseEntity.status(200).body(usuarioService.buscarUsuarioPorId(id));
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioEntity> login(@RequestBody UsuarioLoginDTO usuario) {
        if (usuarioService.validarLogin(usuario)) {
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }
}
