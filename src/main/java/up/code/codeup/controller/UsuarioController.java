package up.code.codeup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import up.code.codeup.ListaObj;
import up.code.codeup.dto.usuarioDto.UsuarioCriacaoDto;
import up.code.codeup.dto.usuarioDto.UsuarioLoginDTO;
import up.code.codeup.dto.usuarioDto.UsuarioTokenDto;
import up.code.codeup.entity.Usuario;
import up.code.codeup.service.ProcessamentoService;
import up.code.codeup.service.UsuarioService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Data
public class UsuarioController {
    private UsuarioService usuarioService;
    private ProcessamentoService processamentoService;
    public UsuarioController (UsuarioService service, ProcessamentoService processamentoService){
        this.processamentoService = processamentoService;
        this.usuarioService = service;
    }

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.buscarUsuarios();
        return ResponseEntity.status(200).body(usuarios);
    }

    @PostMapping("/cadastrar")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> cadastrarUsuario(@RequestBody @Valid UsuarioCriacaoDto usuarioCriacaoDto) {
        this.usuarioService.criar(usuarioCriacaoDto);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable int id, @RequestBody Usuario usuarioAtualizado) {
        if (usuarioService.atualizarUsuario(usuarioAtualizado, id) != null) {
            return ResponseEntity.status(200).body(usuarioAtualizado);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Usuario> deletarUsuario(@PathVariable int id) {
        if (usuarioService.deletarUsuario(id)) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
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

//    @GetMapping(value = "/download-ordenado", produces = "text/csv")
//    public ResponseEntity<Resource> ordenarCsv() throws IOException {
//        List<Usuario> usuarios = usuarioService.buscarUsuarios();
//        ListaObj<Usuario> usuarioListaObj = new ListaObj(usuarios.size());
//
//        for(int i = 0; i < usuarios.size(); i++){
//            usuarioListaObj.adiciona(usuarios.get(i));
//        }
//
//        // Selection sort otimizado
//        for (int i = 0; i < usuarioListaObj.getTamanho() - 1; i++) {
//            int indiceMaior = i;
//            for (int j = i + 1; j < usuarioListaObj.getTamanho(); j++) {
//                if (usuarioListaObj.buscaPorIndice(j).getNivel() > usuarioListaObj.buscaPorIndice(indiceMaior).getNivel()) {
//                    indiceMaior = j;
//                }
//            }
//
//            Usuario usuarioAux = usuarioListaObj.buscaPorIndice(i);
//            usuarioListaObj.substitui(i, usuarioListaObj.buscaPorIndice(indiceMaior));
//            usuarioListaObj.substitui(indiceMaior, usuarioAux);
//        }
//
//
//        String nomeArquivo = "usuarios";
//        usuarioService.gravaUsuariosEmArquivoCsv(usuarioListaObj, nomeArquivo);
//
//        // Carregue o arquivo CSV
//        File csvFile = new File("usuarios.csv");
//        FileInputStream fileInputStream = new FileInputStream(csvFile);
//        InputStreamResource resource = new InputStreamResource(fileInputStream);
//
//        return ResponseEntity.status(200).header(
//                        "content-disposition", "attachment; filename=\"usuarios.csv\"")
//                .body(resource);
//    }

//    @GetMapping(value = "/download", produces = "text/csv")
//    public ResponseEntity<Resource> downloadCsv() throws IOException {
//        List<Usuario> usuarios = usuarioService.buscarUsuarios();
//        ListaObj<Usuario> usuarioListaObj = new ListaObj(usuarios.size());
//
//        for(int i = 0; i < usuarios.size(); i++){
//            usuarioListaObj.adiciona(usuarios.get(i));
//        }
//
//        String nomeArquivo = "usuarios";
//        usuarioService.gravaUsuariosEmArquivoCsv(usuarioListaObj, nomeArquivo);
//
//        File csvFile = new File("usuarios.csv");
//        FileInputStream fileInputStream = new FileInputStream(csvFile);
//        InputStreamResource resource = new InputStreamResource(fileInputStream);
//
//        return ResponseEntity.status(200).header(
//                "content-disposition", "attachment; filename=\"usuarios.csv\"")
//                .body(resource);
//    }

    @GetMapping(value = "/download", produces = "text/txt")
    public ResponseEntity<Resource> downloadTxt() throws IOException {
        List<Usuario> usuarios = usuarioService.buscarUsuarios();
        ListaObj<Usuario> usuarioListaObj = new ListaObj(usuarios.size());

        for(int i = 0; i < usuarios.size(); i++){
            usuarioListaObj.adiciona(usuarios.get(i));
        }

        String nomeArquivo = "usuarios";
        usuarioService.gravaArquivoTxt(usuarioListaObj, nomeArquivo);

        File txtFile = new File("usuarios");
        FileInputStream fileInputStream = new FileInputStream(txtFile);
        InputStreamResource resource = new InputStreamResource(fileInputStream);

        return ResponseEntity.status(200).header(
                        "content-disposition", "attachment; filename=\"usuarios.txt\"")
                .body(resource);
    }

    @PostMapping("/upload")
    public  ResponseEntity<List<UsuarioCriacaoDto>> handleFileUpload(@RequestParam("files") @NotNull List<MultipartFile> files) {
        System.out.println("Entrou aqui");
        for (MultipartFile file : files) {
            processamentoService.getFila().enfileirar(file);
        }
        List<UsuarioCriacaoDto> executarAgendado = processamentoService.executarAgendado();

            if(executarAgendado == null){
                return ResponseEntity.badRequest().build();
            }
        return ResponseEntity.ok().body(executarAgendado);
    }
}