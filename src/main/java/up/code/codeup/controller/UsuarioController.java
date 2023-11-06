package up.code.codeup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.code.codeup.dto.usuarioDto.UsuarioCriacaoDto;
import up.code.codeup.dto.usuarioDto.UsuarioDetalhesCriacaoDto;
import up.code.codeup.dto.usuarioDto.UsuarioLoginDTO;
import up.code.codeup.dto.usuarioDto.UsuarioTokenDto;
import up.code.codeup.entity.Usuario;
import up.code.codeup.mapper.UsuarioMapper;
import up.code.codeup.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = service.listar();
        return ResponseEntity.status(200).body(usuarios);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDetalhesCriacaoDto> cadastrar(@RequestBody @Valid UsuarioCriacaoDto usuarioCriacaoDto) {
        Usuario novoUsuario = UsuarioMapper.of(usuarioCriacaoDto);
        return ResponseEntity.status(201).body(new UsuarioDetalhesCriacaoDto(service.cadastrar(novoUsuario)));
    }


    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable int id) {
        if (service.buscarUsuarioPorId(id) != null) {
            return ResponseEntity.status(200).body(service.buscarUsuarioPorId(id));
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDTO usuarioLoginDTO) {
        UsuarioTokenDto usuarioToken = this.service.autenticar(usuarioLoginDTO);
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
}
