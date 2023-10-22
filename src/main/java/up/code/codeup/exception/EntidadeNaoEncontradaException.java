package up.code.codeup.exception;

public class EntidadeNaoEncontradaException extends RuntimeException{
    public EntidadeNaoEncontradaException(String entidade) {
        super(
                String.format("Não foi possivel encontrar o(a) %s!", entidade)
        );
    }

    
}
