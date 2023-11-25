package up.code.codeup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.code.codeup.dto.lojaDto.ItemLojaDto;
import up.code.codeup.dto.lojaDto.LojaCompletaDto;
import up.code.codeup.entity.ItemLoja;
import up.code.codeup.service.LojaService;
import up.code.codeup.utils.UsuarioUtils;

import java.util.List;

@RestController
@RequestMapping("/loja")
@RequiredArgsConstructor
public class LojaController {
    private final UsuarioUtils usuarioUtils;
    private final LojaService service;

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<LojaCompletaDto> buscarLojaCompleta() {
        List<ItemLoja> itens = service.buscarItensLoja();
        if (itens.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<String> tipoDosItens = itens.stream().map(ItemLoja::getTipo).distinct().toList();
        List<ItemLojaDto> itensLoja = itens.stream()
                .map(itemLoja -> {
                    boolean adquirido = usuarioUtils.usuarioPossuiItem(itemLoja);
                    return new ItemLojaDto(itemLoja, adquirido);
                }).toList();
        return ResponseEntity.status(200).body(new LojaCompletaDto(tipoDosItens, itensLoja));
    }

    @PostMapping("/comprar/{idItem}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> comprarItem(@PathVariable int idItem) {
        service.comprarItem(idItem);
        return ResponseEntity.status(201).build();
    }


}
