package up.code.codeup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import up.code.codeup.dto.usuarioDto.UsuarioCriacaoDto;
import up.code.codeup.dto.usuarioDto.UsuarioLoginDTO;
import up.code.codeup.dto.usuarioDto.UsuarioTokenDto;
import up.code.codeup.entity.Usuario;
import up.code.codeup.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private UsuarioService usuarioService;
    public UsuarioController (UsuarioService service){
        this.usuarioService = service;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.buscarUsuarios();
        return ResponseEntity.status(200).body(usuarios);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Void> cadastrarUsuario(@RequestBody @Valid UsuarioCriacaoDto usuarioCriacaoDto) {
        this.usuarioService.criar(usuarioCriacaoDto);
        return ResponseEntity.status(201).build();
    }

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

    @GetMapping("/exibirCsv")
    public ResponseEntity<String> exibirCsv() {
        //ModelAndView modelAndView = new ModelAndView("csv");
        usuarioService.lerExibirArquivoCsv("usuarios");
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/salvarCsv")
    public String salvarCsv(@RequestParam("file") MultipartFile file) {
        // Aqui você pode chamar o serviço para salvar o arquivo CSV no banco de dados
        usuarioService.saveUsuariosFromCsv(file);
        return "redirect:/exibirCsv"; // Redireciona para a página de exibição do CSV
    }

    @GetMapping("/exportar-csv")
    public ResponseEntity<String> exportarUsuariosParaCsv() {
        List<Usuario> usuarios = usuarioService.buscarUsuarios(); // Substitua por um método que obtém a lista de usuários do banco de dados.
        String nomeArquivo = "usuarios"; // Nome do arquivo CSV
        usuarioService.gravaUsuariosEmArquivoCsv(usuarios, nomeArquivo);
        return ResponseEntity.ok("Arquivo CSV exportado com sucesso.");
    }
}
