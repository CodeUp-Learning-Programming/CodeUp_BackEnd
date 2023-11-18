package up.code.codeup.dto.lojaDto;

import up.code.codeup.entity.ItemLoja;

public record ItemLojaAdquiridoLoginDto(
        String nomeItem,
        String tipoItem,
        Integer precoItem,
        String descricaoItem,
        boolean equipado
) {
    public ItemLojaAdquiridoLoginDto(ItemLoja itemLoja, boolean equipado) {
        this(
                itemLoja.getNomeItem(),
                itemLoja.getTipo(),
                itemLoja.getPreco().intValue(),
                itemLoja.getDescricao(),
                equipado
        );
    }
}
