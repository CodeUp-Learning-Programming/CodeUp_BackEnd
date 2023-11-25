package up.code.codeup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.code.codeup.dto.usuarioDto.*;
import up.code.codeup.entity.Usuario;
import up.code.codeup.mapper.UsuarioMapper;
import up.code.codeup.service.UsuarioService;
import up.code.codeup.utils.UsuarioUtils;

@RestController
@RequestMapping("/usuarios")
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
    public ResponseEntity<Void> atualizarFotoPerfil(@RequestBody @NotNull String novaFoto) {
        service.atualizarFotoPerfil(novaFoto);
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
}
