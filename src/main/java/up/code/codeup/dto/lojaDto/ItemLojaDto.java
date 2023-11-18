package up.code.codeup.dto.lojaDto;

import up.code.codeup.entity.ItemLoja;

public record ItemLojaDto(
        String nomeItem,
        String tipoItem,
        Integer precoItem,
        String descricaoItem,
        boolean adquirido
) {
    public ItemLojaDto(ItemLoja itemLoja, boolean adquirido) {
        this(
                itemLoja.getNomeItem(),
                itemLoja.getTipo(),
                itemLoja.getPreco().intValue(),
                itemLoja.getDescricao(),
                adquirido
        );
    }
}
