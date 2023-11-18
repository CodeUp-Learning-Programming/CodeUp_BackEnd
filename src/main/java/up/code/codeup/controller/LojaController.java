package up.code.codeup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.code.codeup.dto.lojaDto.ItemLojaDto;
import up.code.codeup.dto.lojaDto.LojaCompletaDto;
import up.code.codeup.entity.ItemLoja;
import up.code.codeup.repository.ItemAdquiridoRepository;
import up.code.codeup.repository.ItemLojaRepository;
import up.code.codeup.utils.UsuarioUtils;

import java.util.List;

@RestController
@RequestMapping("/loja")
@RequiredArgsConstructor
public class LojaController {
    private final ItemLojaRepository itemLojaRepository;
    private final UsuarioUtils usuarioUtils;

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<LojaCompletaDto> buscarLojaCompleta() {
        List<ItemLoja> itens = itemLojaRepository.findAll();
        List<String> tipoDosItens = itens.stream().map(ItemLoja::getTipo).distinct().toList();

        List<ItemLojaDto> itensLoja = itens.stream()
                .map(itemLoja -> {
                    boolean aduirido = usuarioUtils.usuarioPossuiItem(itemLoja);
                    return new ItemLojaDto(itemLoja, aduirido);
                }).toList();

        return ResponseEntity.status(200).body(new LojaCompletaDto(tipoDosItens, itensLoja));
    }
}
