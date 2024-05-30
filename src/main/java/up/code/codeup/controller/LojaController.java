package up.code.codeup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import up.code.codeup.dto.lojaDto.ItemLojaDto;
import up.code.codeup.dto.lojaDto.LojaCompletaDto;
import up.code.codeup.dto.usuarioDto.UsuarioAtualizado;
import up.code.codeup.dto.usuarioDto.UsuarioDetalhesPerfil;
import up.code.codeup.entity.ItemLoja;
import up.code.codeup.service.ExercicioUsuarioService;
import up.code.codeup.service.LojaService;
import up.code.codeup.service.UsuarioService;
import up.code.codeup.utils.UsuarioUtils;

import java.util.List;

@RestController
@RequestMapping("api/loja")
@RequiredArgsConstructor
public class LojaController {
    private final UsuarioUtils usuarioUtils;
    private final LojaService service;

    public LojaController(LojaService service, UsuarioUtils usuarioUtils) {
        this.service = service;
        this.usuarioUtils = usuarioUtils;
    }

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<LojaCompletaDto> buscarLojaCompleta() {
        System.out.println(usuarioUtils.getUsuarioLogadoCompleto().getNome());
        List<ItemLoja> itens = service.buscarItensLoja();
        if (itens.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        for (ItemLoja item : itens) {
            System.out.println(item.getNome());
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
    public ResponseEntity<UsuarioAtualizado> comprarItem(@PathVariable int idItem) {
        return ResponseEntity.status(201).body(new UsuarioAtualizado(service.comprarItem(idItem)));
    }
}
