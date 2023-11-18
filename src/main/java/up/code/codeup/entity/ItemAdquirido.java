package up.code.codeup.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.service.annotation.GetExchange;

@Entity
@Getter
@Setter
public class ItemAdquirido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private ItemLoja itemLoja;
    private boolean equipado;
}
