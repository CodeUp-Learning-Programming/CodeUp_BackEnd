package up.code.codeup.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import up.code.codeup.entity.PilhaObj;

@Service
public class Pilha {
    PilhaObj<String>pilhaRefazer = new PilhaObj<>(100);
    PilhaObj<String>pilhaDesfazer = new PilhaObj<>(100);

    public String desfazer() {
        if (pilhaDesfazer.isFull()) {
            throw new IllegalStateException("Pilha esta cheia");
        } else if (pilhaDesfazer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        else {
            pilhaRefazer.push(pilhaDesfazer.peek());
            return pilhaDesfazer.pop();
        }
    }

    public String refazer() {
        if (pilhaRefazer.isFull()) {
            throw new IllegalStateException("Pilha esta cheia");
        } else if (pilhaRefazer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } else {
            pilhaDesfazer.push(pilhaRefazer.peek());
            return pilhaRefazer.pop();
        }

    }

    public void salvaDefazer(String funcao){
        if (pilhaDesfazer.isFull()) {
            throw new IllegalStateException("Pilha esta cheia");
        }else {
            pilhaDesfazer.push(funcao);
        }
    }

}
