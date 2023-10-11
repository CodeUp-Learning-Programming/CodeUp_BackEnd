package up.code.codeup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import up.code.codeup.entity.Exercicio;
import up.code.codeup.service.ExercicioService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/exercicios")
public class ExercicioController {

    @Autowired
    private ExercicioService exercicioService;
    @GetMapping("/{id_fase}/{num_exercicio}")
    public ResponseEntity<Exercicio> buscarExercicio(@PathVariable Integer id_fase, @PathVariable Integer num_exercicio){
        Exercicio exercicioDesejado = this.exercicioService.buscarExercicio(id_fase, num_exercicio);
        return ResponseEntity.status(200).body(exercicioDesejado);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadArquivo(@RequestParam("file") MultipartFile file) throws IOException {
        Exercicio exercicio = new Exercicio();
        exercicio.setNomeArquivo(file.getOriginalFilename());

        // Configure o campo de arquivo diretamente com os bytes do arquivo
        exercicio.setConteudoArquivo(file.getBytes());

        exercicioService.uploadExercicioCSV(file);

        return ResponseEntity.ok("Arquivo salvo com sucesso.");
    }

}
