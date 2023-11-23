package up.code.codeup.service;

import org.springframework.security.core.parameters.P;
import up.code.codeup.entity.PilhaObj;

public class Pilha<T> {
    PilhaObj<T>pilhaRefazer = new PilhaObj<>(10);
    PilhaObj<T>pilhaDesfazer = new PilhaObj<>(10);

    public T desfazer() {
        if (pilhaDesfazer.isFull()) {
            throw new IllegalStateException("Pilha esta cheia");
        } else if (pilhaDesfazer.isEmpty()) {
            throw new IllegalStateException("Pilha esta vazia");
        }
        else {
            pilhaRefazer.push(pilhaDesfazer.peek());
            return pilhaDesfazer.pop();
        }
    }

    public T refazer() {
        if (pilhaRefazer.isFull()) {
            throw new IllegalStateException("Pilha esta cheia");
        } else if (pilhaRefazer.isEmpty()) {
            throw new IllegalStateException("Pilha esta vazia");
        } else {
            pilhaDesfazer.push(pilhaRefazer.peek());
            return pilhaRefazer.pop();
        }

    }

    public void salvaDefazer(T obg){
        if (pilhaDesfazer.isFull()) {
            throw new IllegalStateException("Pilha esta cheia");
        }else {
            pilhaDesfazer.push(obg);
        }
    }

}
