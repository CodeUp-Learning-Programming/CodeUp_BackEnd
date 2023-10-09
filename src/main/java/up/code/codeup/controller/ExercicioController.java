package up.code.codeup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.code.codeup.entity.Exercicio;
import up.code.codeup.service.ExercicioService;

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
}
