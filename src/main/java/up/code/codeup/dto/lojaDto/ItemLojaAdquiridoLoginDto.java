package up.code.codeup.dto.lojaDto;

import up.code.codeup.entity.ItemLoja;

import java.util.Base64;

public record ItemLojaAdquiridoLoginDto(
        String nomeItem,
        String fotoItem,
        String tipoItem,
        Integer precoItem,
        String descricaoItem,
        boolean equipado
) {
    public ItemLojaAdquiridoLoginDto(ItemLoja itemLoja, boolean equipado) {
        this(
                itemLoja.getNomeItem(),
                itemLoja.getImagem() != null ? Base64.getEncoder().encodeToString(itemLoja.getImagem()) : null,
                itemLoja.getTipo(),
                itemLoja.getPreco().intValue(),
                itemLoja.getDescricao(),
                equipado
        );
    }
}
