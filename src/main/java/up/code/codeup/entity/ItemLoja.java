package up.code.codeup.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class ItemLoja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;
    private String tipo;
    private Integer preco;
    private String imagem;
    @OneToMany(mappedBy = "itemLoja")
    private List<ItemAdquirido> itemAdquirido;
}
