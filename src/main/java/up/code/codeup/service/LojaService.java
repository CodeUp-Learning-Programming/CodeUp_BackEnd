package up.code.codeup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import up.code.codeup.entity.ItemAdquirido;
import up.code.codeup.entity.ItemLoja;
import up.code.codeup.entity.Usuario;
import up.code.codeup.repository.ItemAdquiridoRepository;
import up.code.codeup.repository.ItemLojaRepository;
import up.code.codeup.repository.UsuarioRepository;
import up.code.codeup.utils.UsuarioUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LojaService {
    private final ItemLojaRepository itemLojaRepository;
    private final ItemAdquiridoRepository itemAdquiridoRepository;
    private final UsuarioUtils usuarioUtils;
    private final UsuarioRepository usuarioRepository;

    public List<ItemLoja> buscarItensLoja() {
        return itemLojaRepository.findAll();
    }

    public void comprarItem(int idItem) {
        ItemLoja item = itemLojaRepository.findById(idItem).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));
        Usuario usuario = usuarioUtils.getUsuarioLogadoCompleto();
        if (usuario.getMoedas() < item.getPreco()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Moedas insuficientes");
        }
        usuario.getItemAdquiridos().forEach(itemAdquirido -> {
            if (itemAdquirido.getItemLoja().getId() == idItem) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Item já adquirido");
            }
        });
        ItemAdquirido itemAdquirido = new ItemAdquirido();
        itemAdquirido.setItemLoja(item);
        itemAdquirido.setUsuario(usuario);
        itemAdquirido.setEquipado(false);
        usuario.setMoedas(usuario.getMoedas() - item.getPreco());
        usuarioRepository.save(usuario);
        itemAdquiridoRepository.save(itemAdquirido);
    }
}