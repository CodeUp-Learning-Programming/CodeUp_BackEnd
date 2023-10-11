package up.code.codeup.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

@Entity
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomeArquivo;

    @Lob
    private byte[] conteudoArquivo;
    private Integer num_exercicio;

    private boolean concluido;

    private String conteudo_teorico;
    private String desafio;

    private String instrucao;

    private String layout_funcao;

    private String resposta;

    @ManyToOne
    @JoinColumn(name = "fk_fase")
    @JsonBackReference
    private Fase fase;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public byte[] getConteudoArquivo() {
        return conteudoArquivo;
    }

    public void setConteudoArquivo(byte[] conteudoArquivo) {
        this.conteudoArquivo = conteudoArquivo;
    }

    public Integer getNum_exercicio() {
        return num_exercicio;
    }

    public void setNum_exercicio(Integer num_exercicio) {
        this.num_exercicio = num_exercicio;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    public String getConteudo_teorico() {
        return conteudo_teorico;
    }

    public void setConteudo_teorico(String conteudo_teorico) {
        this.conteudo_teorico = conteudo_teorico;
    }

    public String getDesafio() {
        return desafio;
    }

    public void setDesafio(String desafio) {
        this.desafio = desafio;
    }

    public String getInstrucao() {
        return instrucao;
    }

    public void setInstrucao(String instrucao) {
        this.instrucao = instrucao;
    }

    public String getLayout_funcao() {
        return layout_funcao;
    }

    public void setLayout_funcao(String layout_funcao) {
        this.layout_funcao = layout_funcao;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }
}
