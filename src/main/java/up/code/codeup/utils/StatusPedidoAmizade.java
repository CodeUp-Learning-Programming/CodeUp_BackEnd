package up.code.codeup.utils;

public enum StatusPedidoAmizade {
    PENDENTE(0),
    ACEITO(1),
    RECUSADO(2);

    private final int valor;

    StatusPedidoAmizade(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static StatusPedidoAmizade getByValor(int valor) {
        for (StatusPedidoAmizade status : StatusPedidoAmizade.values()) {
            if (status.getValor() == valor) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status de pedido de amizade inválido: " + valor);
    }
}

