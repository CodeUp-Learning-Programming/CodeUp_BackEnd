package up.code.codeup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import up.code.codeup.entity.ItemLoja;
import up.code.codeup.repository.ItemLojaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LojaService {
    private final ItemLojaRepository itemLojaRepository;

    public List<ItemLoja> buscarItensLoja() {
        return itemLojaRepository.findAll();
    }
}
