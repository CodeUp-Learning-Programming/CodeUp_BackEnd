package up.code.codeup.tests;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;
import up.code.codeup.tests.JsResult;

@RequestMapping("/teste")
@RestController
public class JsValidation {

    @GetMapping
    public ResponseEntity<JsResult> testJavaScriptCode(@RequestParam String funcao) {
        JsResult jsResult = new JsResult();

        try (Context context = Context.create()) {
            // Execute o bloco de código JavaScript usando Graal.js
            Value resultado = context.eval("js", funcao);

            // Configure o resultado no objeto JsResult
            jsResult.setResultado(resultado.as(Object.class));
            
            // Retorne a resposta HTTP com o objeto JsResult
            return ResponseEntity.ok(jsResult);
        } catch (PolyglotException e) {
            // Lidar com exceções, por exemplo, logar o erro
            System.err.println("Erro ao executar código JavaScript: " + e.getMessage());
            jsResult.setResultado("Erro ao executar código JavaScript. Verifique o log para mais detalhes.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsResult);
        }
    }
}
