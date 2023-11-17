package up.code.codeup;

public class FilaObj<T> {

    private Object[] fila;
    private int tamanho;
    private int capacidade;

    public FilaObj(int capacidade) {
        this.fila = new Object[capacidade];
        this.tamanho = 0;
        this.capacidade = capacidade;
    }

    public void enfileirar(T elemento) {
        if (tamanho < capacidade) {
            fila[tamanho] = elemento;
            tamanho++;
        } else {
            System.out.println("Fila cheia. Não é possível enfileirar mais elementos.");
        }
    }

    public T desenfileirar() {
        if (tamanho > 0) {
            T elemento = (T) fila[0];
            System.arraycopy(fila, 1, fila, 0, tamanho - 1);
            tamanho--;
            return elemento;
        } else {
            System.out.println("Fila vazia. Não é possível desenfileirar.");
            return null;
        }
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }
}
