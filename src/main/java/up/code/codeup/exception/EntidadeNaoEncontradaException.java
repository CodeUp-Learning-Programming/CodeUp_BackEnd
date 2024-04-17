package up.code.codeup.exception;

import org.springframework.web.client.HttpClientErrorException;

public class EntidadeNaoEncontradaException extends RuntimeException {
    public EntidadeNaoEncontradaException(String entidade) {
        super(
                String.format("Não foi possivel encontrar o(a) %s!", entidade)
        );
    }


}
