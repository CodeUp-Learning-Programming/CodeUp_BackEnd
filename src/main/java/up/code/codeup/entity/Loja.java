package up.code.codeup.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Loja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomeLoja;
    @OneToMany(mappedBy = "loja")
    private List<ItemLoja> getItensLoja;
}
