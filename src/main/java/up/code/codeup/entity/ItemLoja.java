package up.code.codeup.entity;

import jakarta.persistence.*;

@Entity
public class ItemLoja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomeItem;
    private String descricao;
    private String tipo;
    private Double preco;
    private byte[] imagem;
    @ManyToOne
    private Loja loja;
}
