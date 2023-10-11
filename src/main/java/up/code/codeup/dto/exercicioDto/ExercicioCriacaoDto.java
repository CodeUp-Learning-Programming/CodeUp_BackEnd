package up.code.codeup.dto.exercicioDto;

import up.code.codeup.entity.Fase;

public class ExercicioCriacaoDto {

    private Integer num_exercicio;

    private String nomeArquivo;
    private String conteudoArquivo;

    private boolean concluido;

    private Fase fase;

    public Integer getNum_exercicio() {
        return num_exercicio;
    }

    public void setNum_exercicio(Integer num_exercicio) {
        this.num_exercicio = num_exercicio;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public byte[] getConteudoArquivo() {
        return conteudoArquivo.getBytes();
    }

    public void setConteudoArquivo(String conteudoArquivo) {
        this.conteudoArquivo = conteudoArquivo;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }
}
