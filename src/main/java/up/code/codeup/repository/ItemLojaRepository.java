package up.code.codeup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import up.code.codeup.entity.ItemLoja;

import java.util.List;

public interface ItemLojaRepository extends JpaRepository<ItemLoja, Integer> {
    List<ItemLoja> findAll();
}
