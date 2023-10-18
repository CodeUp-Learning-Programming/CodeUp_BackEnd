package up.code.codeup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import up.code.codeup.arquivo.ListaObj;
import up.code.codeup.dto.usuarioDto.UsuarioCriacaoDto;
import up.code.codeup.dto.usuarioDto.UsuarioLoginDTO;
import up.code.codeup.dto.usuarioDto.UsuarioTokenDto;
import up.code.codeup.entity.Usuario;
import up.code.codeup.service.UsuarioService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

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

/*
    @PostMapping("/salvarCsv")
    public String salvarCsv(@RequestParam("file") MultipartFile file) {
        // Aqui você pode chamar o serviço para salvar o arquivo CSV no banco de dados
        usuarioService.saveUsuariosFromCsv(file);
        return "redirect:/exibirCsv"; // Redireciona para a página de exibição do CSV
    }
*/

    @GetMapping("/exportar-csv")
    public ResponseEntity<String> exportarUsuariosParaCsv() {
        List<Usuario> usuarios = usuarioService.buscarUsuarios(); // Substitua por um método que obtém a lista de usuários do banco de dados.

        ListaObj<Usuario> usuarioListaObj = new ListaObj(usuarios.size());

        for(int i = 0; i < usuarios.size(); i++){
            usuarioListaObj.adiciona(usuarios.get(i));
        }

        // Selection sort otimizado
        for (int i = 0; i < usuarioListaObj.getTamanho() - 1; i++) {
                int indiceMenor = i;
                for (int j = i + 1; j < usuarioListaObj.getTamanho(); j++) {
                    if (usuarioListaObj.buscaPorIndice(j).getNivel() < usuarioListaObj.buscaPorIndice(indiceMenor).getNivel()) {
                        indiceMenor = j;
                    }
                }

                Usuario usuarioAux = usuarioListaObj.buscaPorIndice(i);
                usuarioListaObj.substitui(i, usuarioListaObj.buscaPorIndice(indiceMenor));
                usuarioListaObj.substitui(indiceMenor, usuarioAux);
        }

        String nomeArquivo = "usuarios"; // Nome do arquivo CSV
        usuarioService.gravaUsuariosEmArquivoCsv(usuarioListaObj, nomeArquivo);
        return ResponseEntity.ok("Arquivo CSV exportado com sucesso.");
    }

    @GetMapping(value = "/download", produces = "text/csv")
    public ResponseEntity<Resource> downloadCsv() throws IOException {
        // Carregue o arquivo CSV
        File csvFile = new File("usuarios.csv");
        FileInputStream fileInputStream = new FileInputStream(csvFile);
        InputStreamResource resource = new InputStreamResource(fileInputStream);

        return ResponseEntity.status(200).header(
                "content-disposition", "attachment; filename=\"usuarios.csv\"")
                .body(resource);
    }


}
