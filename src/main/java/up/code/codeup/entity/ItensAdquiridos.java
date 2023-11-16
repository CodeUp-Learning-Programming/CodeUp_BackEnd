package up.code.codeup.entity;

import jakarta.persistence.*;

@Entity
public class ItensAdquiridos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private ItemLoja itemLoja;
    private boolean equipado;
}
